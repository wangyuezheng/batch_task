package com.wyz.config;

import org.apache.http.HeaderElement; 
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.Args;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.Assert;

/**
 * 
 * @author liyuyuan_vendor
 *
 */
@Configuration
public class EsConfig {
	private static final Logger log = LoggerFactory.getLogger(EsConfig.class);

	static final String COLON = ":";
	static final String COMMA = ",";

	@Value("${spring.data.elasticsearch.cluster-name}")
	private String clusterName;

	@Value("${spring.data.elasticsearch.cluster-nodes}")
	private String clusterAddress;

	@Bean
    public RestHighLevelClient client() {
		String[] nodes = clusterAddress.split(COMMA);
		HttpHost[] hosts = new HttpHost[nodes.length];

		for (int i = 0, j = nodes.length; i < j; i++) {
			String hostName = nodes[i].split(COLON)[0];
			String port = nodes[i].split(COLON)[1];
			Assert.hasText(hostName, "[Assertion failed] missing host name in 'clusterNodes'");
			Assert.hasText(port, "[Assertion failed] missing port in 'clusterNodes'");
			log.info("adding transport node : " + nodes[i]);
			hosts[i] = new HttpHost(hostName, Integer.parseInt(port));
		}

		RestClientBuilder builder = RestClient.builder(hosts).setHttpClientConfigCallback(callback -> {
			callback.disableAuthCaching();
			return callback.setKeepAliveStrategy((response, context) -> {
				Args.notNull(response, "HTTP response");
				final HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					final HeaderElement he = it.nextElement();
					final String param = he.getName();
					final String value = he.getValue();
					if (value != null && "timeout".equalsIgnoreCase(param)) {
						try {
							return Long.parseLong(value) * 1000;
						} catch (final NumberFormatException ignore) {
						}
					}
				}
				return 10 * 1000;
			});
		});
		RestHighLevelClient restHighLevelClient = new RestHighLevelClient(builder);
		return restHighLevelClient;
	}

}

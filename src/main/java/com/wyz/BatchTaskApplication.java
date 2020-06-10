package com.wyz;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @ClassName BatchTaskApplication
 * @Description //TODO
 * @Author wangyuezheng
 * @Date 2020/6/10 15:30
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
public class BatchTaskApplication {
    public static void main(String[] args) {
        SpringApplication.run(BatchTaskApplication.class);
    }
}

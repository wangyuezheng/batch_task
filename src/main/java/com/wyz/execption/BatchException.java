package com.wyz.execption;

import com.wyz.enums.ExceptionEnum;
import lombok.Getter;

/**
 * @ClassName BatchException
 * @Description 自定义批量任务异常
 * @Author wangyuezheng
 * @Date 2020/6/9 18:40
 */
@Getter
public class BatchException extends Exception {
    /**
     * code 码
     */
    private final String code;

    /**
     * 错误描述
     */
    private final String msg;


    public BatchException(String code, String msg) {
        super(msg);
        this.code = code;
        this.msg = msg;
    }


    public BatchException(ExceptionEnum exceptionEnum,String msg) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
        this.msg = String.format(exceptionEnum.getMsg(),msg);
    }

    public BatchException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMsg());
        this.code = exceptionEnum.getCode();
        this.msg = exceptionEnum.getMsg();
    }

}

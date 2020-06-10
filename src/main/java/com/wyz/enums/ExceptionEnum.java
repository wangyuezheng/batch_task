package com.wyz.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @ClassName ExceptionEnum
 * @Description 自定义异常枚举类
 * @Author wangyuezheng
 * @Date 2020/6/9 18:43
 */
@AllArgsConstructor
@Getter
public enum  ExceptionEnum {

    FORK_JOIN_GET_ERROR("0001","fork/join 获取结果集失败，失败消息: %s"),
    DO_TASKING_ERROR("0002","任务执行出现异常");


    private String code;

    private String msg;

}

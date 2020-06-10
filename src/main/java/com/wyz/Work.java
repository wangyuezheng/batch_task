package com.wyz;


import com.wyz.task.BatchTask;

import java.util.List;

/**
 * @ClassName Work
 * @Description 工作模板接口
 * @Author wangyuezheng
 * @Date 2020/5/31 11:20
 */
public interface Work<T,E> {
    /**
     *  任务的业务逻辑
     * @param ts
     * @return
     */
    T doWork(List<E> ts);


    /**
     *
     * @param result
     * @return
     */
    T result(List<T> result);
}

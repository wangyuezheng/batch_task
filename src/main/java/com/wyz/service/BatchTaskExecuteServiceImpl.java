package com.wyz.service;


import com.wyz.Work;
import com.wyz.enums.ExceptionEnum;
import com.wyz.execption.BatchException;
import com.wyz.task.BatchTask;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * @ClassName BatchTaskExecuteServiceImpl
 * @Description //TODO
 * @Author wangyuezheng
 * @Date 2020/6/8 19:23
 */

public class BatchTaskExecuteServiceImpl<T,E> {

    public  T executor(int threshold, List<E> list, Work<T,E> wark) throws BatchException {
        ForkJoinPool pool = new ForkJoinPool();
        BatchTask task = new BatchTask(threshold,list,wark);
        ForkJoinTask<T> forkJoinTask = pool.submit(task);
        if (task.isCompletedAbnormally()) {
            throw new BatchException(ExceptionEnum.DO_TASKING_ERROR);
    }
        T t ;
        try {
            t = forkJoinTask.get();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BatchException(ExceptionEnum.FORK_JOIN_GET_ERROR,e.getMessage());
        }
        return t;
    }

    private static List<Integer> getDataSource(int i) {
        List<Integer> list = new ArrayList<>();
        if (i <= 0){
            return list;
        }
        for (int i1 = 1; i1 <= i; i1++) {
            list.add(i1);
        }
        return list;
    }


 }
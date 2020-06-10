package com.wyz.task;

import com.wyz.Work;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

/**
 * @ClassName BatchTask
 * @Description 批量任务
 * @Author wangyuezheng
 * @Date 2020/5/31 11:20
 */
public  class BatchTask<T,E> extends RecursiveTask<T> {
    /**
     * 请求阈值 用于拆分任务
     */
    private final int threshold;
    /**
     *  任务列表
     */
    private List<E> list;
    /**
     * 工作线程
     */
    private Work<T,E> work;


    /**
     *  构造批量任务对象
     * @param threshold
     * @param list
     * @param wark
     */
    public BatchTask(int threshold, List<E> list,Work<T,E> wark) {
        if (threshold <= 0) {
            throw new IllegalArgumentException("threshold:参数不合法！");
        }
        if(list ==null || list.size() ==0){
            throw new IllegalArgumentException("list内容不能为空！");
        }
        this.threshold = threshold;
        this.list = list;
        this.work = wark;
    }

    /**
     * 执行任务
     * @return
     */
    @Override
    protected T compute() {
        //获取任务总数量
        int total = list.size();
        //判断任务是否需要拆分
        if (total <= threshold) {
            //执行任务，人会执行结果
            T t = work.doWork(list);
            return  t;
        }
        //获取任任务拆分为两半
        int half = total / 2;

        //获取左子任务数据源
        List<E> leftTaskList = subList(0, half , list);
        //左子任务
        BatchTask<T,E> leftBatchTask = new BatchTask<>(threshold, leftTaskList, work);
        //获取右子任务数据源
        List<E> rightTaskList = subList(half, total - half , list);
        //右子任务
        BatchTask<T,E> rightBatchTask = new BatchTask<>(threshold, rightTaskList, work);
        List<T> lists = new ArrayList<>();
        //执行左子任务
        leftBatchTask.fork();
        //执行右子任务
        rightBatchTask.fork();
        //使用join 获取两者的结果集返回
        T left = leftBatchTask.join();
        lists.add(left);
        T right = rightBatchTask.join();
        lists.add(right);
        return work.result(lists);
    }

    /**
     *  拆分任务
     * @param startIndex
     * @param count
     * @param list
     * @return
     */
    private  List<E> subList(int startIndex , int count , List<E> list){
        List<E> eList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            eList.add(list.get(startIndex));
            startIndex++;
        }
        return eList;
    }
}


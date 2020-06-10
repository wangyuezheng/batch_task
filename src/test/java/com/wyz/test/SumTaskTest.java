package com.wyz.test;

import com.wyz.execption.BatchException;
import com.wyz.service.BatchTaskExecuteServiceImpl;
import com.wyz.task.SumTask;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SumTaskTest
 * @Description //TODO
 * @Author wangyuezheng
 * @Date 2020/6/9 19:12
 */

public class SumTaskTest {
    public static void main(String[] args) {
        BatchTaskExecuteServiceImpl<Long,Integer> service = new BatchTaskExecuteServiceImpl();
        List<Integer> dataSource = getDataSource(10000000);
        try {
            long millis = System.currentTimeMillis();
            Long executor = service.executor(1000000, dataSource, new SumTask());
            System.out.println("fork/join计算结果:" + executor);
            System.out.println("fork/join计算耗时"+(System.currentTimeMillis()-millis));
        } catch (BatchException e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();
        Long sum =  executor(dataSource);
        System.out.println("单线程计算结果:" + sum);
        System.out.println("单线程计算耗时"+(System.currentTimeMillis()-start));
    }

    private static Long executor(List<Integer> dataSource) {

        long sum = dataSource.stream().mapToLong(x -> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return x;
        }).sum();
        return sum;
    }


    private static List<Integer> getDataSource(int count){
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            list.add(i);
        }
        return list;
    }
}

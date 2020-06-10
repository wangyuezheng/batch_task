package com.wyz.test;

import com.wyz.execption.BatchException;
import com.wyz.service.BatchTaskExecuteServiceImpl;
import com.wyz.task.FindMaxTask;

import java.util.List;

/**
 * @ClassName FindMaxTaskTest
 * @Description //TODO
 * @Author wangyuezheng
 * @Date 2020/6/9 20:22
 */

public class FindMaxTaskTest {
    public static void main(String[] args) {
        BatchTaskExecuteServiceImpl<Integer,Integer> service = new BatchTaskExecuteServiceImpl();
        try {
            Integer executor = service.executor(3, getDataSource(), new FindMaxTask());
            System.out.println(executor);
        } catch (BatchException e) {
            e.printStackTrace();
        }
    }

    private static List<Integer> getDataSource(){
        //jdk9新特性
        List<Integer> list = List.of(1, 2, 12, 434, 12, 34, 454, 55, 767, 45645, 78, 78, 7, 45, 123, 54, 667, 23, 234, 6, 7, 24234);
        return list;
    }

}

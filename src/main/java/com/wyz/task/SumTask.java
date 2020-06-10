package com.wyz.task;

import com.wyz.Work;

import java.util.List;

/**
 * @ClassName SumTask
 * @Description 求合任务类
 * @Author wangyuezheng
 * @Date 2020/6/9 19:08
 */

public class SumTask implements Work<Long,Integer> {


    @Override
    public Long doWork(List<Integer> ts) {

        long sum = ts.stream().mapToLong(x -> x).sum();
        return sum;
    }

    @Override
    public Long result(List<Long> longs) {
        Long sum = 0L;
        for (Long aLong : longs) {
            sum += aLong;
        }
        return sum;
    }
}

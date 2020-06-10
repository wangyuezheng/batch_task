package com.wyz.task;

import com.wyz.Work;

import java.util.List;

/**
 * @ClassName FindMaxTask
 * @Description //TODO
 * @Author wangyuezheng
 * @Date 2020/6/9 20:18
 */

public class FindMaxTask implements Work<Integer,Integer> {
    @Override
    public Integer doWork(List<Integer> ts) {
        return findMaxValue(ts);
    }

    @Override
    public Integer result(List<Integer> result) {
        return findMaxValue(result);
    }

    public Integer findMaxValue(List<Integer> ts){
        Integer maxValue = 0;
        for (int i = 0; i < ts.size(); i++) {
            if (ts.get(i) > maxValue){
                maxValue = ts.get(i);
            }
        }
        return maxValue;
    }
}

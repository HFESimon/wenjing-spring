package com.dubbo.framework.balance.impl;

import com.dubbo.framework.URL;
import com.dubbo.framework.balance.Balance;
import com.dubbo.framework.protocol.invoker.Invoker;

import java.util.List;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 8:32 下午
 */

public class AverageBalance implements Balance {

    private static int average = 0;

    @Override
    public Invoker doBalance(List<Invoker> invokers) {
        return invokers.get(average++ % invokers.size());
    }
}

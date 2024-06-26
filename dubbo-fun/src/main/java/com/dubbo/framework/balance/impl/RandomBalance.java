package com.dubbo.framework.balance.impl;

import com.dubbo.framework.URL;
import com.dubbo.framework.balance.Balance;
import com.dubbo.framework.protocol.invoker.Invoker;

import java.util.List;
import java.util.Random;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:41 上午
 */

public class RandomBalance implements Balance {

    @Override
    public Invoker doBalance(List<Invoker> invokers) {
        Random random = new Random();
        int n = random.nextInt(invokers.size());
        return invokers.get(n);
    }
}

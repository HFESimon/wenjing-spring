package com.dubbo.framework.balance.impl;

import com.dubbo.framework.URL;
import com.dubbo.framework.balance.Balance;

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
    public URL doBalance(List<URL> urls) {
        return urls.get(average++ % urls.size());
    }
}

package com.dubbo.framework.balance;

import com.dubbo.framework.URL;
import com.dubbo.framework.protocol.invoker.Invoker;

import java.util.List;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 8:28 下午
 */
public interface Balance {

    Invoker doBalance(List<Invoker> invokers);
}

package com.dubbo.framework.balance.factory;

import com.dubbo.framework.balance.Balance;
import com.dubbo.framework.balance.BalanceEnum;
import com.dubbo.framework.balance.impl.AverageBalance;
import com.dubbo.framework.balance.impl.RandomBalance;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 8:27 下午
 */
public class BalanceFactory {

    public static Balance getBalance(BalanceEnum balance) {

        switch (balance) {
            case RANDOM:
                return new RandomBalance();
            case AVERAGE:
                return new AverageBalance();
            default:
                break;
        }
        return new AverageBalance();
    }
}

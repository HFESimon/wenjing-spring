package com.dubbo.framework.balance.invoker;

import com.dubbo.framework.URL;
import com.dubbo.framework.balance.BalanceEnum;
import com.dubbo.framework.balance.factory.BalanceFactory;
import com.dubbo.framework.protocol.Protocol;
import com.dubbo.framework.protocol.dto.Invocation;
import com.dubbo.framework.protocol.factory.ProtocolFactory;
import com.dubbo.framework.protocol.invoker.Invoker;
import com.dubbo.framework.register.ZookeeperRegister;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/3 1:45 下午
 */

@Data
public class ClusterInvoker implements Invoker {

    List<Invoker> invokers = new ArrayList<>();

    public void addInvokers(Invoker invoker) {
        this.invokers.add(invoker);
    }

    public static Invoker join(Class<?> interfaceClass) {
        ClusterInvoker clusterInvoker = new ClusterInvoker();

        // 从注册中心查看urls
        List<URL> urlList = ZookeeperRegister.get(interfaceClass.getName());

        urlList.forEach(url -> {
            Protocol protocol = ProtocolFactory.getProtocol(url.getProtocol());
            Invoker invoker = protocol.refer(url);
            clusterInvoker.addInvokers(invoker);
        });

        return clusterInvoker;
    }

    @Override
    public String invoke(Invocation invocation) {
        Invoker invoker = BalanceFactory.getBalance(BalanceEnum.RANDOM).doBalance(invokers);
        return invoker.invoke(invocation);
    }
}

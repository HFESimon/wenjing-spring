# dubbo-fun


环境准备:
```text
1.jdk1.8
2.maven
3.zookeeper
```
run demo:
```text
1. mvn clean install 引入依赖
2. 启动本地zookeeper -> ex: zookeeper-3.4.10/bin/zkServer.sh start
3. 启动 dubbo-provider -> provider/Provider1#main provider/Provider2#main
4. 启动 dubbo-consumer -> consumer/Consumer#main
```


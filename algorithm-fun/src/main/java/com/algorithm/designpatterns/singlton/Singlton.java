package com.algorithm.designpatterns.singlton;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/15 7:13 下午
 */
public class Singlton {

    public volatile static Singlton instance = new Singlton();

    private Singlton() {}

    /**
     * 提供公共的访问方法，使用双重检查锁定确保线程安全
     * @return
     */
    public static Singlton getInstance() {
        if (instance == null) {
            synchronized (Singlton.class) {
                if (instance == null) {
                    instance = new Singlton();
                }
            }
        }
        return instance;
    }
}

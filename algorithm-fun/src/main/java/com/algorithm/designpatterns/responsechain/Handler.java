package com.algorithm.designpatterns.responsechain;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/15 7:07 下午
 */
public abstract class Handler {

    protected Handler successHandler;

    public void setSuccessHandler(Handler successHandler) {
        this.successHandler = successHandler;
    }

    public abstract void handle(int req);
}

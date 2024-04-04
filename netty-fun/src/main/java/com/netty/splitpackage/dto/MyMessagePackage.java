package com.netty.splitpackage.dto;

import lombok.Data;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/4 10:00 下午
 */

@Data
public class MyMessagePackage {

    private int len;

    private byte[] content;
}

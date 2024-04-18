package com.dubbo.framework.protocol.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:46 上午
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invocation implements Serializable {

    private String interfaceName;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] parameters;
}

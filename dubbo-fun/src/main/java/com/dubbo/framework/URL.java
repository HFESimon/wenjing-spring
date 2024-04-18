package com.dubbo.framework;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/18 10:38 上午
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class URL {

    private String hostName;

    private Integer port;
}

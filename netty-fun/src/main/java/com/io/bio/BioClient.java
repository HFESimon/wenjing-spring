package com.io.bio;

import java.io.IOException;
import java.net.Socket;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/4/17 1:19 上午
 */
public class BioClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9000);
        //向服务端发送数据
        socket.getOutputStream().write("HelloServer".getBytes());
        socket.getOutputStream().flush();
        System.out.println("向服务端发送数据结束");
        byte[] bytes = new byte[1024];
        //接收服务端回传的数据
        int read = socket.getInputStream().read(bytes);
        System.out.println("接收到服务端的数据：" + new String(bytes, 0, read));
        socket.close();
    }
}

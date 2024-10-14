package com.yly.nio.文件编程.filechannel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class FileChannelTransferToDemo {


    @Test
    public void test() {
        try (
                FileChannel from = new FileInputStream("src/test/resources/data.txt").getChannel();
                FileChannel to = new FileOutputStream("src/test/resources/to.txt").getChannel();
        ) {
            // 效率高，底层会利用操作系统的零拷贝进行优化, 2g 数据
            long size = from.size();
            // left 变量代表还剩余多少字节
            for (long left = size; left > 0; ) {
                System.out.println("position:" + (size - left) + " left:" + left);
                left -= from.transferTo((size - left), left, to);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

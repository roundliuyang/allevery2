package com.yly.nio.文件编程.filechannel;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileChannelDemo {


    @Test
    public void test() {
        try {
            FileChannel channel = new FileInputStream("src/test/resources/data.txt").getChannel();
            // 准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            // 从 channel 读取数据，向buffer 写入
            try {
                channel.read(buffer);
                // 打印

                buffer.flip();
                while (buffer.hasRemaining()) {
                    byte b = buffer.get();
                    System.out.println((char) b);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void writeFileIntoDiskNIO() {
        try {
            Path dirPath = Paths.get("src/test/resources/");
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }

            Path filePath = dirPath.resolve("1.txt");
            try (FileChannel outChannel = FileChannel.open(filePath, StandardOpenOption.CREATE, StandardOpenOption.WRITE);
                 InputStream fis = new FileInputStream("src/test/resources/data.txt")) {

                FileChannel inChannel = (FileChannel) Channels.newChannel(fis);
                ByteBuffer buffer = ByteBuffer.allocate(4096);

                while (inChannel.read(buffer) > 0) {
                    buffer.flip();
                    outChannel.write(buffer);
                    buffer.clear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

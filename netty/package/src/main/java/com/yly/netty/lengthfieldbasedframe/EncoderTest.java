package com.yly.netty.lengthfieldbasedframe;

import com.yly.netty.MyLoggingHandler;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

import java.nio.charset.StandardCharsets;

public class EncoderTest {

    public static void main(String[] args) {
        // 使用EmbeddedChannel测试handler
        EmbeddedChannel channel = new EmbeddedChannel(
                new LengthFieldBasedFrameDecoder(1024, 0, 4, 1, 5),
                new MyLoggingHandler()
        );

        // 模拟客户端，写入数据
        ByteBuf buffer = ByteBufAllocator.DEFAULT.buffer();
        send(buffer, "Hello1");
        channel.writeInbound(buffer);
        send(buffer, "World12");
        channel.writeInbound(buffer);
    }

    private static void send(ByteBuf buf, String message) {
        // 得到数据的长度
        int length = message.length();
        byte[] bytes = message.getBytes(StandardCharsets.UTF_8);
        // 数据长度标识为4个字节
        buf.writeInt(length);
        // 附加信息
        buf.writeByte(0x01);
        // 具体数据
        buf.writeBytes(bytes);
    }
}

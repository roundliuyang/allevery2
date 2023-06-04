package com.yly.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import static io.netty.buffer.ByteBufUtil.appendPrettyHexDump;
import static io.netty.util.internal.StringUtil.NEWLINE;

public class MyLoggingHandler extends ChannelInboundHandlerAdapter {

    /**
     * @param ctx 上下文对象，含有管道、通道、地址
     * @param msg 客户端发送的数据，默认Object
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("server ctx = " + ctx);
        // 将msg转成一个ByteBuf
        ByteBuf byteBuf = (ByteBuf) msg;
        int length = byteBuf.readableBytes();
        int rows = length / 16 + (length % 15 == 0 ? 0 : 1) + 4;
        StringBuilder str = new StringBuilder(rows * 80 * 2)
                .append("read index:").append(byteBuf.readerIndex())
                .append(" write index:").append(byteBuf.writerIndex())
                .append(" capacity:").append(byteBuf.capacity())
                .append(NEWLINE);

        appendPrettyHexDump(str, byteBuf);
        System.out.println(str.toString());
    }

    // 处理异常，关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}

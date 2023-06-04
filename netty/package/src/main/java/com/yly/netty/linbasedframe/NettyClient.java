package com.yly.netty.linbasedframe;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class NettyClient {

    public static void main(String[] args) throws InterruptedException {
        send();
    }

    private static void send() throws InterruptedException {
        EventLoopGroup eventExecutors = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInboundHandlerAdapter() {
                        @Override
                        public void channelActive(ChannelHandlerContext ctx) throws Exception {
                            ByteBuf buffer = ctx.alloc().buffer(38);

                            for (int i = 0; i < 3; i++) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(UUID.randomUUID().toString());
                                sb.append("\n");
                                buffer.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8));
                            }
                            ctx.writeAndFlush(buffer);
                        }
                    });

            System.out.println("客户端 ok");

            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1", 6668).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            eventExecutors.shutdownGracefully();
        }
    }

}


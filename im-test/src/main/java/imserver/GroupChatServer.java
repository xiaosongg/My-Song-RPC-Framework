package imserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author wusong
 * @create 2023/5/10
 **/
public class GroupChatServer {
    //监听端口
    private int port;

    public GroupChatServer(int port){
        this.port = port;
    }

    //编写run方法，处理客户端请求
    public void run() throws InterruptedException {
        //创建一个处理连接的线程组，处理连接用一个线程就行了
        NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //创建一个处理请求的线程组，默认是电脑处理器数量的两倍
        NioEventLoopGroup workerGroup = new NioEventLoopGroup();

        try {
            //创建服务器端启动类
            ServerBootstrap server = new ServerBootstrap();
            //将两个线程组加入到服务器端中
            server.group(bossGroup,workerGroup)
                    //指定通道的类型
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipeline = ch.pipeline();
                            //加入管道处理器
                            pipeline.addLast("decoder",new StringDecoder())
                                    .addLast("encoder",new StringEncoder())
                                    //这个是自定义的处理器，我们在其中写我们的业务
                                    .addLast("ServerHandler",new GroupChatServerHandler());
                        }
                    });
            System.out.println("netty 服务器启动");
            ChannelFuture future = server.bind(port).sync();
            //监听关闭事件
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new GroupChatServer(8000).run();
    }
}


package imserver;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wusong
 * @create 2023/5/10
 **/
public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    //定义一个channel组，管理所有的channel
    //GlobalEventExecutor.INSTANCE 是全局的事件执行器，是一个单例
    private static final ChannelGroup CHANNEL_GROUP = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private String getCurrentTimeString() {
        return SIMPLE_DATE_FORMAT.format(new Date());
    }

    //handlerAdded表示一旦连接建立，该方法第一个被执行
    //将当前channel加入到channelGroup
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入聊天的信息推送给其他在线的客户端
        //该方法会将channelGroup中所有channel 遍历，并发送消息，无需自己遍历
        CHANNEL_GROUP.writeAndFlush(getCurrentTimeString() + "[" + channel.remoteAddress() + "]加入聊天\n");
        CHANNEL_GROUP.add(channel);
    }

    //表示channel处于活动状态，提示xxx上线（服务端显示）
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(getCurrentTimeString() + "[客户端]" + ctx.channel().remoteAddress() + "上线了");
    }

    //读取客户端数据,并且转发信息到其他客户端
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //读取数据
        Channel channel = ctx.channel();
        //这时我们遍历channelGroup，根据不同情况，回送不同信息
        CHANNEL_GROUP.forEach(ch -> {
            if (channel != ch) {
                ch.writeAndFlush(getCurrentTimeString() + "[" + channel.remoteAddress() + "]:" + msg + "\n");
            } else {
                ch.writeAndFlush(getCurrentTimeString() + "[自己]:" + msg + "\n");
            }
        });
    }

    //表示channel处于不活动状态，提示xxx下线了
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(getCurrentTimeString() + "[客户端]" + ctx.channel().remoteAddress() + "离线了\n");
    }

    //表示客户端断开连接
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        CHANNEL_GROUP.writeAndFlush(getCurrentTimeString() + "[" + channel.remoteAddress() + "]离开了\n");
    }

    //发生异常，关闭通道
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx .close();
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        CHANNEL_GROUP.writeAndFlush("当前聊天室人数:" + CHANNEL_GROUP.size() + "人");
    }
}



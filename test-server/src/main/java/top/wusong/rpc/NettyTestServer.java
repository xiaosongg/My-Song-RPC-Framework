package top.wusong.rpc;

import top.wusong.rpc.netty.server.NettyServer;
import top.wusong.rpc.registry.DefaultServiceRegistry;
import top.wusong.rpc.registry.ServiceRegistry;
import top.wusong.rpc.serializer.KryoSerializer;

/**
 * @author wusong
 * @create 2023/5/9
 **/
public class NettyTestServer {

    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry registry = new DefaultServiceRegistry();
        registry.register(helloService);
        NettyServer server = new NettyServer();
        server.setSerializer(new KryoSerializer());
        server.start(9999);
    }

}


package top.wusong.rpc;

import top.wusong.rpc.registry.DefaultServiceRegistry;
import top.wusong.rpc.registry.ServiceRegistry;
import top.wusong.rpc.server.RpcServer;

/**
 * @author wusong
 * @create 2023/5/9
 **/
public class TestServer {
    public static void main(String[] args) {

        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}


package top.wusong.rpc;

import top.wusong.rpc.server.RpcServer;

/**
 * @author wusong
 * @create 2023/5/9
 **/
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(helloService, 9000);
    }
}


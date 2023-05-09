package top.wusong.rpc;

import top.wusong.rpc.client.RpcClientProxy;
import top.wusong.rpc.netty.client.NettyClient;
import top.wusong.rpc.serializer.HessianSerializer;

/**
 * @author wusong
 * @create 2023/5/9
 **/
public class NettyTestClient {

    public static void main(String[] args) {
        RpcClient client = new NettyClient("127.0.0.1", 9999);
        client.setSerializer(new HessianSerializer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        HelloObject object = new HelloObject(12, "This is a message");
        String res = helloService.hello(object);
        System.out.println(res);

    }

}


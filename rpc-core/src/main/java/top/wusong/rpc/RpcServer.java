package top.wusong.rpc;

import top.wusong.rpc.serializer.CommonSerializer;

/**
 * @author wusong
 * @create 2023/5/9
 **/
public interface RpcServer {
    void start(int port);

    void setSerializer(CommonSerializer serializer);
}

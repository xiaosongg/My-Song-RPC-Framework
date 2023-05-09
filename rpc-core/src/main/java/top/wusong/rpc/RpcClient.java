package top.wusong.rpc;

import top.wusong.rpc.entity.RpcRequest;
import top.wusong.rpc.serializer.CommonSerializer;

/**
 * @author wusong
 * @create 2023/5/9
 **/
public interface RpcClient {

    Object sendRequest(RpcRequest rpcRequest);

    void setSerializer(CommonSerializer serializer);

}

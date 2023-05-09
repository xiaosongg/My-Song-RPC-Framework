package top.wusong.rpc.exception;

import top.wusong.rpc.enumeration.RpcError;

/**
 * @author wusong
 * @create 2023/5/9
 **/
public class RpcException extends RuntimeException {

    public RpcException(RpcError error, String detail) {
        super(error.getMessage() + ": " + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcError error) {
        super(error.getMessage());
    }

}

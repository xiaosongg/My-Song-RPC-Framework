package top.wusong.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wusong
 * @create 2023/5/9
 **/
@AllArgsConstructor
@Getter
public enum SerializerCode {

    KRYO(0),
    JSON(1),
    HESSIAN(2);

    private final int code;

}

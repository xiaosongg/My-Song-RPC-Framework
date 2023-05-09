package top.wusong.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wusong
 * @create 2023/5/9
 **/
@AllArgsConstructor
@Getter
public enum PackageType {

    REQUEST_PACK(0),
    RESPONSE_PACK(1);

    private final int code;

}


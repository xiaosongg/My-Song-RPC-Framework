package top.wusong.rpc;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wusong
 * @create 2023/5/9
 **/
@Data
@AllArgsConstructor
public class HelloObject implements Serializable {

    private Integer id;
    private String message;

}

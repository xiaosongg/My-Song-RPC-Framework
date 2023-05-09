package top.wusong.rpc.registry;

/**
 * @author wusong
 * @create 2023/5/9
 **/
public interface ServiceRegistry {

    <T> void register(T service);

    Object getService(String serviceName);
}

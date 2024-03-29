package top.wusong.rpc.server;

/**
 * @author wusong
 * @create 2023/5/9
 **/
//public class WorkerThread implements Runnable{
//
//    private static final Logger logger = LoggerFactory.getLogger(WorkerThread.class);
//
//    private Socket socket;
//    private Object service;
//
//    public WorkerThread(Socket socket, Object service) {
//        this.socket = socket;
//        this.service = service;
//    }

//    @Override
//    public void run() {
//
//        try (ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
//             ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())) {
//
//            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
//
//            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
//
//            // 通过method.invoke方法，传入对象实例和参数，即可调用并且获得返回值。
//            Object returnObject = method.invoke(service, rpcRequest.getParameters());
//
//            objectOutputStream.writeObject(RpcResponse.success(returnObject));
//
//            objectOutputStream.flush();
//        } catch (IOException | ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//
//            logger.error("调用或发送时有错误发生：", e);
//        }
//    }

//}

package top.wusong.rpc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.wusong.rpc.registry.ServiceRegistry;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author wusong
 * @create 2023/5/9
 **/
public class RpcServer {

    private static final Logger logger = LoggerFactory.getLogger(RpcServer.class);

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAXIMUM_POOL_SIZE = 50;
    private static final int KEEP_ALIVE_TIME = 60;
    private static final int BLOCKING_QUEUE_CAPACITY = 100;
    private final ExecutorService threadPool;
    private RequestHandler requestHandler = new RequestHandler();
    private final ServiceRegistry serviceRegistry;

//    public RpcServer() {
//        int corePoolSize = 5;
//        int maximumPoolSize = 50;
//        long keepAliveTime = 60;
//        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(100);
//        ThreadFactory threadFactory = Executors.defaultThreadFactory();
//        threadPool = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, workingQueue, threadFactory);
//    }

    public RpcServer(ServiceRegistry serviceRegistry) {
        this.serviceRegistry = serviceRegistry;
        BlockingQueue<Runnable> workingQueue = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS, workingQueue, threadFactory);
    }


    public void start(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("服务器启动……");
            Socket socket;
            while((socket = serverSocket.accept()) != null) {
                logger.info("消费者连接: {}:{}", socket.getInetAddress(), socket.getPort());
                threadPool.execute(new RequestHandlerThread(socket, requestHandler, serviceRegistry));
            }
            threadPool.shutdown();
        } catch (IOException e) {
            logger.error("服务器启动时有错误发生:", e);
        }
    }


//    public void register(Object service, int port) {
//        // 使用一个ServerSocket监听某个端口，循环接收连接请求，如果发来了请求就创建一个线程，在新线程中处理调用
//        try (ServerSocket serverSocket = new ServerSocket(port)) {
//            logger.info("服务器正在启动...");
//            Socket socket;
//            while((socket = serverSocket.accept()) != null) {
//                logger.info("客户端连接！Ip为：" + socket.getInetAddress());
//                threadPool.execute(new WorkerThread(socket,  service));
//            }
//        } catch (IOException e) {
//            logger.error("连接时有错误发生：", e);
//        }
//    }


}

package cn.edu.nju.p.core;

import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.concurrent.ThreadPoolExecutor;

public class RpcServerLoader {

    private volatile static RpcServerLoader rpcServerLoader;
    private final static String DELIMITER = ":";

    private final static int PARALLEL = Math.max(Runtime.getRuntime().availableProcessors(), 4); // the available processors on jvm

    private EventLoopGroup eventExecutors = new NioEventLoopGroup(PARALLEL); // create executors corresponding with PARALLEL
    private static ThreadPoolExecutor executor = (ThreadPoolExecutor) RpcThreadPool.getExecutor(16, -1);

    // to be continue

}

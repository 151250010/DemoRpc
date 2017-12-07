package cn.edu.nju.p.core;

import java.util.concurrent.*;

public class RpcThreadPool {

    public static Executor getExecutor(int threads, int queues) {
        String name = "RpcThreadPool";
        return new ThreadPoolExecutor(threads, threads, 0, TimeUnit.MILLISECONDS,
                queues == 0 ? new SynchronousQueue<>()
                        : (queues < 0 ? new LinkedBlockingDeque<>() : new LinkedBlockingDeque<>(queues)),
                new NamedThreadFactory(name, true));
    }
}

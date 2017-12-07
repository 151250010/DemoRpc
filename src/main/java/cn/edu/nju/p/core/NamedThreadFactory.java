package cn.edu.nju.p.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * thread factory to create thread : daemon thread with prefix
 */
public class NamedThreadFactory implements ThreadFactory{

    private static final AtomicInteger threadNumber = new AtomicInteger(1);
    private final AtomicInteger mThreadNum = new AtomicInteger(1);
    private final String prefix;
    private final boolean daemonThread;
    private final ThreadGroup threadGroup;

    public NamedThreadFactory(String prefix, boolean daemonThread, ThreadGroup threadGroup) {
        this.prefix = prefix;
        this.daemonThread = daemonThread;
        this.threadGroup = threadGroup;
    }

    public NamedThreadFactory(String prefix, boolean daemonThread) {
        this.prefix = prefix + "-thread-";
        this.daemonThread = daemonThread;
        SecurityManager securityManager = System.getSecurityManager();
        threadGroup = securityManager == null ? Thread.currentThread().getThreadGroup() : securityManager.getThreadGroup();
    }

    public NamedThreadFactory(String prefix) {
        this(prefix, false);
    }

    public NamedThreadFactory(){
        this("rpcserver-thread-pool-" + threadNumber.getAndIncrement(), false);
    }

    public Thread newThread(Runnable r) {
        String name = prefix + mThreadNum.getAndIncrement();
        Thread ret = new Thread(threadGroup, r, name, 0);
        ret.setDaemon(daemonThread);
        return ret;
    }

    public ThreadGroup getThreadGroup() {
        return threadGroup;
    }
}

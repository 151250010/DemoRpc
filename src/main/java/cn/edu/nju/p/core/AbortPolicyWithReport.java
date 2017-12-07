package cn.edu.nju.p.core;

import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;

public class AbortPolicyWithReport extends ThreadPoolExecutor.AbortPolicy {

    private final String threadName;

    public AbortPolicyWithReport(String threadName) {
        this.threadName = threadName;
    }

    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {

        String message = String.format("RpcServer[" +
                        " Thread Name: %s, Pool Size: %d (active: %d, core: %d, max: %d, largest: %d), " +
                        " Task: %d (completed: %d)," +
                        " Executor Status:(isShutdown: %s, isTerminated: %s, isTerminating: %s)]"
                , threadName, executor.getPoolSize(), executor.getActiveCount(), executor.getCorePoolSize(), executor.getMaximumPoolSize(), executor.getLargestPoolSize()
                , executor.getTaskCount(), executor.getCompletedTaskCount()
                , executor.isShutdown(), executor.isTerminated(), executor.isTerminating());

        System.out.println(message);
        throw new RejectedExecutionException(message);
    }
}

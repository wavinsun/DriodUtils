package cn.mutils.app.droid.utils.os;

import java.util.Comparator;
import java.util.concurrent.Executor;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 线程池
 */
public class ThreadPool implements Executor {

    /**
     * 高优先级
     */
    public static final int HIGH_PRIORITY = 1;

    /**
     * 默认优先级
     */
    public static final int NORMAL_PRIORITY = 2;

    /**
     * 低优先级
     */
    public static final int LOW_PRIORITY = 3;

    private static final int CORE_POOL_SIZE = 5;
    private static final int MAX_POOL_SIZE = 256;
    private static final int KEEP_ALIVE = 1;

    private static final AtomicLong sThreadCounter = new AtomicLong(1);
    private static final ThreadFactory sThreadFactory = new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "ThreadPool-" + sThreadCounter.getAndIncrement());
        }
    };
    private static final Comparator<Runnable> sBlockingQueueComparator = new Comparator<Runnable>() {
        @Override
        public int compare(Runnable lhs, Runnable rhs) {
            if (!(lhs instanceof PriorityRunnable && rhs instanceof PriorityRunnable)) {
                return 0;
            }
            return ((PriorityRunnable) lhs).mPriority - ((PriorityRunnable) rhs).mPriority;
        }
    };

    private static volatile ThreadPool sDefaultPool;

    private final ThreadPoolExecutor mPoolExecutor;
    private final PriorityBlockingQueue<Runnable> mPoolQueue = new PriorityBlockingQueue<Runnable>(
            MAX_POOL_SIZE, sBlockingQueueComparator);

    public ThreadPool() {
        this(CORE_POOL_SIZE);
    }

    public ThreadPool(int poolSize) {
        mPoolExecutor = new ThreadPoolExecutor(poolSize,
                MAX_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, mPoolQueue, sThreadFactory);
    }

    /**
     * 执行任务
     *
     * @param command 任务
     */
    @Override
    public void execute(Runnable command) {
        if (command == null) {
            return;
        }
        mPoolExecutor.execute(command);
    }

    /**
     * 根据优先级执行任务
     *
     * @param command  任务
     * @param priority 优先级
     */
    public void execute(Runnable command, int priority) {
        mPoolExecutor.execute(new PriorityRunnable(command, priority));
    }

    /**
     * 获取线程池运行并行线程数目
     */
    public int getPoolSize() {
        return mPoolExecutor.getCorePoolSize();
    }

    /**
     * 设置线程池并行线程数目
     */
    public void setPoolSize(int poolSize) {
        if (poolSize > 0) {
            mPoolExecutor.setCorePoolSize(poolSize);
        }
    }

    /**
     * 线程池是否满负荷运行
     */
    public boolean isBusy() {
        return mPoolExecutor.getActiveCount() >= mPoolExecutor.getCorePoolSize();
    }

    /**
     * 停止线程池
     */
    public void shutdown() {
        mPoolExecutor.shutdown();
    }

    /**
     * 获取默认线程池
     *
     * @return 默认线程池
     */
    public static ThreadPool defaultPool() {
        if (sDefaultPool == null) {
            synchronized (ThreadPool.class) {
                if (sDefaultPool == null) {
                    sDefaultPool = new ThreadPool();
                }
            }
        }
        return sDefaultPool;
    }

    private static class PriorityRunnable implements Runnable {
        private Runnable mCommand;
        private int mPriority;

        public PriorityRunnable(Runnable command, int priority) {
            mCommand = command;
            mPriority = priority;
        }

        @Override
        public void run() {
            if (mCommand != null) {
                mCommand.run();
            }
        }
    }
}

package cn.mutils.app.droid.utils.os;

import java.util.concurrent.Executor;

/**
 * 任务执行，支持回调
 */
public class TaskExecutor {

    private TaskExecutor() {

    }

    /**
     * 通过ThreadPool封装的默认线程池启动
     *
     * @param task 任务
     * @return 任务
     */
    public static <T> Task<T> startByPool(Task<T> task) {
        return startByPool(task, ThreadPool.defaultPool());
    }

    /**
     * 通过ThreadPool线程池启动
     *
     * @param task 任务
     * @param pool 线程池
     * @return 任务
     */
    public static <T> Task<T> startByPool(Task<T> task, ThreadPool pool) {
        pool.execute(task.obtainThreadContext());
        return task;
    }

    /**
     * 通过ThreadPool线程池指定优先级启动
     *
     * @param task     任务
     * @param priority 优先级
     * @param pool     线程池
     * @return 任务
     */
    public static <T> Task<T> startByPool(Task<T> task, int priority, ThreadPool pool) {
        pool.execute(task.obtainThreadContext(), priority);
        return task;
    }

    /**
     * 使用自定义线程池启动任务
     *
     * @param task     任务
     * @param executor 线程池
     * @return 任务
     */
    public static <T> Task<T> start(Task<T> task, Executor executor) {
        executor.execute(task.obtainThreadContext());
        return task;
    }

    /**
     * 启动任务
     *
     * @param task 任务
     * @param <T>  返回类型
     * @return 任务
     */
    public static <T> Task<T> start(Task<T> task) {
        ThreadExecutor.post(task.obtainThreadContext());
        return task;
    }

    /**
     * 任务
     */
    public static abstract class Task<Result> {

        /**
         * 没有状态
         */
        public static final int STATE_NULL = 0;

        /**
         * 等待执行状态
         */
        public static final int STATE_WAITING = 1;

        /**
         * 执行状态
         */
        public static final int STATE_RUNNING = 2;

        /**
         * 执行成功状态
         */
        public static final int STATE_FINISHED = 3;

        /**
         * 执行取消状态
         */
        public static final int STATE_CANCELLED = 4;

        /**
         * 执行失败状态
         */
        public static final int STATE_ERROR = 5;

        private volatile int mState = STATE_NULL;

        private volatile Runnable mThreadContext;

        /**
         * 获取线程执行上下文，支持重复启动
         */
        protected synchronized Runnable obtainThreadContext() {
            mState = Task.STATE_WAITING;
            mThreadContext = new Runnable() {
                @Override
                public void run() {
                    try {
                        if (mState == Task.STATE_CANCELLED) {
                            return;
                        }
                        synchronized (Task.this) {
                            if (mThreadContext != this) {
                                return;
                            }
                            mState = Task.STATE_RUNNING;
                        }
                        UiExecutor.post(new Runnable() {
                            @Override
                            public void run() {
                                onStart();
                            }
                        });
                        final Result result = doBackground();
                        if (mState == Task.STATE_CANCELLED) {
                            return;
                        }
                        synchronized (Task.this) {
                            if (mThreadContext != this) {
                                return;
                            }
                            mState = Task.STATE_FINISHED;
                        }
                        if (mThreadContext == this) {
                            UiExecutor.post(new Runnable() {
                                @Override
                                public void run() {
                                    onFinished(result);
                                }
                            });
                        }
                    } catch (final Throwable e) {
                        synchronized (Task.this) {
                            if (mThreadContext != this) {
                                return;
                            }
                            mState = Task.STATE_ERROR;
                        }
                        UiExecutor.post(new Runnable() {
                            @Override
                            public void run() {
                                onError(e);
                            }
                        });
                    } finally {
                        if (mThreadContext != this) {
                            return;
                        }
                        if (mState == Task.STATE_CANCELLED) {
                            UiExecutor.post(new Runnable() {
                                @Override
                                public void run() {
                                    onCancelled();
                                }
                            });
                        }
                    }
                }
            };
            return mThreadContext;
        }

        /**
         * 子线程执行回调
         *
         * @return 结果
         */
        protected abstract Result doBackground() throws Exception;

        /**
         * 执行完成回调
         *
         * @param result 结果
         */
        protected void onFinished(Result result) {

        }

        /**
         * 执行失败回调
         *
         * @param error 异常
         */
        protected void onError(Throwable error) {

        }

        /**
         * 任务开始回调
         */
        protected void onStart() {

        }

        /**
         * 任务取消回调
         */
        protected void onCancelled() {

        }

        /**
         * 取消任务
         */
        public void cancel() {
            mState = STATE_CANCELLED;
        }

        /**
         * 判断任务是否终止
         */
        public boolean isStopped() {
            return mState > STATE_RUNNING;
        }

        /**
         * 是否任务已经启动
         */
        public boolean isStarted() {
            return mState != STATE_CANCELLED && mState > STATE_NULL;
        }

        /**
         * 任务是否已经取消
         */
        public boolean isCancelled() {
            return mState == STATE_CANCELLED;
        }

    }

}

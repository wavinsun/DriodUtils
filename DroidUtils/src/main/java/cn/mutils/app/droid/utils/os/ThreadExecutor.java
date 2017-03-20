package cn.mutils.app.droid.utils.os;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * 子线程执行工具
 *
 * @author 堇花 Created by wenhua.ywh on 2016/12/14.
 */
public class ThreadExecutor {

    private static final Handler sHandler = new Handler(Looper.getMainLooper());

    private ThreadExecutor() {

    }

    /**
     * post到子线程
     *
     * @param action 回调
     */
    public static void post(Runnable action) {
        postDelayed(action, 0);
    }

    /**
     * post到子线程延时处理
     *
     * @param action     回调
     * @param delayMills 延时
     */
    public static void postDelayed(Runnable action, long delayMills) {
        if (action == null) {
            return;
        }
        Message message = Message.obtain(sHandler, new InnerRunnable(action));
        message.obj = action;
        message.what = action.hashCode();
        sHandler.sendMessageDelayed(message, delayMills);
    }

    /**
     * 移除回调
     *
     * @param action 回调
     */
    public static void removeCallbacks(Runnable action) {
        if (action == null) {
            return;
        }
        sHandler.removeMessages(action.hashCode(), action);
    }

    static class InnerRunnable implements Runnable {

        private Runnable mTarget;

        public InnerRunnable(Runnable target) {
            mTarget = target;
        }

        @Override
        public void run() {
            if (mTarget != null) {
                new InnerTask(mTarget).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
            mTarget = null;
        }

    }

    static class InnerTask extends AsyncTask<Object, Object, Object> {

        private Runnable mAction;

        private InnerTask(Runnable action) {
            mAction = action;
        }

        @Override
        protected Object doInBackground(Object... params) {
            if (mAction != null) {
                mAction.run();
            }
            mAction = null;
            return null;
        }
    }

}

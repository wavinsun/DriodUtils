package cn.mutils.app.droid.utils.os;

import android.os.Looper;

/**
 * 异步任务兼容封装
 *
 * Created by wenhua.ywh on 2017/3/24.
 */
public class AsyncTaskCompat {

    /**
     * 保证初始化异步任务在主线程
     */
    public static void initAsyncTask() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            throw new IllegalThreadStateException("Should be called in main thread!");
        }
        try {
            Class.forName("android.os.AsyncTask");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

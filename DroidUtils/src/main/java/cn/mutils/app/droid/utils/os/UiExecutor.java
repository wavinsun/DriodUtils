package cn.mutils.app.droid.utils.os;

import android.os.Handler;
import android.os.Looper;

/**
 * 主线程执行工具
 *
 * @author 堇花 Created by wenhua.ywh on 2016/12/14.
 */
public class UiExecutor {

    private static final Handler sHandler = new Handler(Looper.getMainLooper());

    private UiExecutor() {

    }

    /**
     * post到主线程
     *
     * @param action 回调
     */
    public static void post(Runnable action) {
        if (action == null) {
            return;
        }
        sHandler.post(action);
    }

    /**
     * post到主线程延时处理
     *
     * 解耦<code>TaskManager#postDelayed(Runnable,long)</code>
     *
     * @param action     回调
     * @param delayMills 延时
     */
    public static void postDelayed(Runnable action, long delayMills) {
        if (action == null) {
            return;
        }
        sHandler.postDelayed(action, delayMills);
    }

    /**
     * 删除回调
     *
     * @param action 回调
     */
    public static void removeCallbacks(Runnable action) {
        if (action == null) {
            return;
        }
        sHandler.removeCallbacks(action);
    }

}

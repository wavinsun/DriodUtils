package cn.mutils.app.droid.utils.os;

import android.app.ActivityManager;
import android.content.Context;

import java.util.List;

/**
 * 进程实用类
 */
public class ProcessUtil {

    /**
     * 判断是否是主进程
     *
     * @param context 上下文
     * @return 是否是主进程
     */
    public static boolean isMainProcess(Context context) {
        String packageName = context.getPackageName();
        String processName = getProcessName(context);
        return packageName.equals(processName);
    }

    /**
     * 获取当前进程名称
     *
     * @param context 上下文
     * @return 当前进程名称
     */
    public static String getProcessName(Context context) {
        final int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = activityManager.getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo appProcess : runningAppProcesses) {
            if (appProcess.pid == pid) {
                return appProcess.processName;
            }
        }
        return null;
    }

}

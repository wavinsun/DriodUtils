package cn.mutils.app.droid.utils.os;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.File;

/**
 * 应用相关实用类
 */
public class AppUtil {

    private AppUtil() {

    }

    /**
     * 获取files目录
     *
     * @param context 上下文
     * @return files目录
     */
    public static File getFilesDir(Context context) {
        File result = context.getFilesDir();
        if (result == null) {
            result = context.getDir("files", 0);
        }
        if (result == null) {
            result = new File("/data/data/" + context.getPackageName() + "/app_files");
        }
        if (!result.exists()) {
            result.mkdirs();
        }
        return result;
    }

    /**
     * 获取缓存目录
     *
     * @param context 上下文
     * @return 缓存目录
     */
    public static File getCacheDir(Context context) {
        File result = context.getCacheDir();
        if (result == null) {
            result = context.getDir("cache", 0);
        }
        if (result == null) {
            result = new File("/data/data/" + context.getPackageName() + "/app_cache");
        }
        if (!result.exists()) {
            result.mkdirs();
        }
        return result;
    }

    /**
     * 获取程序包信息
     *
     * @param context 上下文
     * @return 程序包信息
     */
    public static PackageInfo getPackageInfo(Context context) {
        PackageManager pm = context.getPackageManager();
        try {
            return pm.getPackageInfo(context.getPackageName(), 0);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取4位版本信息的最后一位  例如7.8.2.1005版本则返回1005
     *
     * @param context 上下文
     * @return 打包build版本
     */
    public static String getBuildName(Context context) {
        PackageInfo info = getPackageInfo(context);
        if (info == null) {
            return "";
        }
        String version = info.versionName;
        if (version == null) {
            return "";
        }
        int lastIndexOfSpace = version.lastIndexOf(' ');
        if (lastIndexOfSpace != -1) {
            version = version.substring(0, lastIndexOfSpace);
        }
        String[] versionArray = version.split("[.]");
        if (versionArray.length < 4) {
            return "";
        }
        return versionArray[3];
    }

}

package cn.mutils.app.droid.utils.os;

import android.os.Environment;

import java.io.File;

/**
 * SD卡辅助类
 */
public class SDCardUtil {

    private SDCardUtil() {

    }

    /**
     * 判断SD卡是否可用
     *
     * @return 是否可用
     */
    public static boolean isSDCardEnabled() {
        return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
    }

    /**
     * 获取SD卡路径
     *
     * @return 路径
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

}

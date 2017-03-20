package cn.mutils.app.droid.utils.device;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 常用单位转换
 */
public class DimenUtil {

    /**
     * 将dp转换为px
     *
     * @param context 上下文
     * @param dpValue dp
     * @return px
     */
    public static int dp2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5F);
    }

    /**
     * 将px转换为dp
     *
     * @param context 上下文
     * @param pxValue px
     * @return dp
     */
    public static int px2dp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5F);
    }

    /**
     * 将sp转换为px
     *
     * @param context 上下文
     * @param spValue sp
     * @return px
     */
    public static int sp2px(Context context, float spValue) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5F);
    }

    /**
     * 将px转换为sp
     *
     * @param context 上下文
     * @param pxValue px
     * @return sp
     */
    public static int px2sp(Context context, float pxValue) {
        float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / scale + 0.5F);
    }

    /**
     * 将dp转换为px
     *
     * @param metrics 显示指标
     * @param dpValue dp
     * @return px
     */
    public static int dp2px(DisplayMetrics metrics, float dpValue) {
        float scale = metrics.density;
        return (int) (dpValue * scale + 0.5F);
    }

    /**
     * 将px转换为dp
     *
     * @param metrics 显示指标
     * @param pxValue px
     * @return dp
     */
    public static int px2dp(DisplayMetrics metrics, float pxValue) {
        float scale = metrics.density;
        return (int) (pxValue / scale + 0.5F);
    }

    /**
     * 将sp转换为px
     *
     * @param metrics 显示指标
     * @param spValue sp
     * @return px
     */
    public static int sp2px(DisplayMetrics metrics, float spValue) {
        float scale = metrics.scaledDensity;
        return (int) (spValue * scale + 0.5F);
    }

    /**
     * 将px转换为sp
     *
     * @param metrics 显示指标
     * @param pxValue px
     * @return sp
     */
    public static int px2sp(DisplayMetrics metrics, float pxValue) {
        float scale = metrics.scaledDensity;
        return (int) (pxValue / scale + 0.5F);
    }

}

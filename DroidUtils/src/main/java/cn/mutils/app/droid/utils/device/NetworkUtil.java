package cn.mutils.app.droid.utils.device;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 手机网络信息
 */
public class NetworkUtil {

    private static final String NETWORK_WIFI = "wifi";
    private static final String NETWORK_MOBILE = "mobile";

    /**
     * 未知网络
     */
    public static final int NETWORK_TYPE_UNKNOWN = 0;
    /**
     * 2G网络
     */
    public static final int NETWORK_TYPE_2G = 1;
    /**
     * 3G网络
     */
    public static final int NETWORK_TYPE_3G = 2;
    /**
     * 4G网络
     */
    public static final int NETWORK_TYPE_4G = 3;
    /**
     * Wifi网络
     */
    public static final int NETWORK_TYPE_WIFI = 4;

    private NetworkUtil() {

    }

    /**
     * 获取运营商名称
     *
     * @param context 上下文
     * @return 运营商
     */
    public static String getOperatorName(Context context) {
        if (null == context) {
            return "";
        }
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (null != telephonyManager &&
                telephonyManager.getSimState() == TelephonyManager.SIM_STATE_READY) {
            return telephonyManager.getNetworkOperator();
        } else {
            return "";
        }
    }

    /**
     * 获取网络连接状态
     *
     * @param context 上下文
     * @return wifi mobile
     */
    public static String getNetworkTypeName(Context context) {
        String name = "";
        if (null == context) {
            return name;
        }
        try {
            ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connMgr == null) {
                return name;
            }
            NetworkInfo infoWifi = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo infoMobile = connMgr
                    .getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if (null != infoWifi && infoWifi.isConnected()) {
                name = NETWORK_WIFI;
            }
            if (null != infoMobile && infoMobile.isConnected()) {
                name = NETWORK_MOBILE;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    /**
     * 获取网络类型
     *
     * {@link #NETWORK_TYPE_UNKNOWN} {@link #NETWORK_TYPE_WIFI} {@link #NETWORK_TYPE_2G} {@link
     * #NETWORK_TYPE_3G} {@link #NETWORK_TYPE_4G}
     *
     * @param context 上下文
     * @return 类型
     */
    public static int getNetworkType(Context context) {
        if (null == context) {
            return NETWORK_TYPE_UNKNOWN;
        }
        ConnectivityManager connMgr = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connMgr == null) {
            return NETWORK_TYPE_UNKNOWN;
        }
        NetworkInfo activeNetInfo;
        try {
            activeNetInfo = connMgr.getActiveNetworkInfo();
        } catch (Exception e) {
            return NETWORK_TYPE_UNKNOWN;
        }
        if (activeNetInfo == null) {
            return NETWORK_TYPE_UNKNOWN;
        }
        if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return NETWORK_TYPE_WIFI;
        } else if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return getMobileNetType(context, activeNetInfo);
        }
        return NETWORK_TYPE_UNKNOWN;
    }

    /**
     * 获取手机网络类型
     *
     * {@link #NETWORK_TYPE_UNKNOWN} {@link #NETWORK_TYPE_2G} {@link #NETWORK_TYPE_3G} {@link
     * #NETWORK_TYPE_4G}
     *
     * @param context 上下文
     * @param info    网络信息描述
     * @return 类型
     */
    public static int getMobileNetType(Context context, NetworkInfo info) {
        if (null == context) {
            return NETWORK_TYPE_UNKNOWN;
        }
        TelephonyManager telephonyMgr = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyMgr == null) {
            return NETWORK_TYPE_UNKNOWN;
        }
        switch (telephonyMgr.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_TYPE_2G;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_TYPE_3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_TYPE_4G;
            default:
                if (info != null && info.isConnected()) {
                    String _strSubTypeName = info.getSubtypeName();
                    if ("TD-SCDMA".equalsIgnoreCase(_strSubTypeName) || "WCDMA".equalsIgnoreCase(_strSubTypeName)
                            || "CDMA2000".equalsIgnoreCase(_strSubTypeName)) {
                        return NETWORK_TYPE_3G;
                    }
                }
                return NETWORK_TYPE_UNKNOWN;
        }
    }

    /**
     * 获取手机通信协议的网络制式
     *
     * @param context 上下文
     * @return CDMA2000 WCDMA TD-SCDMA
     */
    public static String getNetworkSubTypeName(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return networkInfo.getSubtypeName();
        }
        return "";
    }

    /**
     * 判断网络是否连接
     *
     * @param context 上下文
     * @return 是否连接
     */
    public static boolean isNetworkConnected(Context context) {
        if (null == context) {
            return false;
        }
        try {
            ConnectivityManager connMgr = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo == null) {
                return false;
            }
            if (NetworkInfo.State.CONNECTED == networkInfo.getState()) {
                int networkType = networkInfo.getType();
                switch (networkType) {
                    case ConnectivityManager.TYPE_WIFI:
                    case ConnectivityManager.TYPE_WIMAX:// 全球微博互联接入 3G
                    case ConnectivityManager.TYPE_BLUETOOTH:
                    case ConnectivityManager.TYPE_ETHERNET:
                    case ConnectivityManager.TYPE_MOBILE:
                        return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 判断是否是http协议
     *
     * @param url 地址
     * @return 是否是http
     */
    public static boolean isHttpProtocol(String url) {
        Pattern httpPattern = Pattern.compile("(http|https):\\/\\/([\\w.]+\\/?)\\S*");
        Matcher matcher = httpPattern.matcher(url);
        return matcher.matches();
    }

}

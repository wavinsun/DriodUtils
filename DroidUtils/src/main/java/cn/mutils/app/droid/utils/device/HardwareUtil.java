package cn.mutils.app.droid.utils.device;

import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiManager;
import android.provider.Settings;

import java.util.List;

/**
 * 硬件检测
 */
public class HardwareUtil {

    private HardwareUtil() {

    }

    /**
     * 是否开启wifi
     *
     * @param context 上下文
     * @return 是否开启
     */
    public static boolean isWifiEnabled(Context context) {
        try {
            WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            return wm.isWifiEnabled();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 是否开启gps
     *
     * @param context 上下文
     * @return 是否开启
     */
    public static boolean isGpsEnabled(Context context) {
        try {
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            return lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 判断是否存在GPS设备
     *
     * @param context 上下文
     * @return 是否存在
     */
    public static boolean hasGpsDevice(Context context) {
        try {
            LocationManager mgr = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            if (mgr == null) {
                return false;
            }
            List<String> providers = mgr.getAllProviders();
            if (providers == null) {
                return false;
            }
            return providers.contains(LocationManager.GPS_PROVIDER);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 是否使用模拟定位
     *
     * @param context 上下文
     * @return 是否使用
     */
    public static boolean isAllowMockLocation(Context context) {
        if (context == null) {
            return true;
        }
        if ("0".equals(Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ALLOW_MOCK_LOCATION))) {
            return false;
        }
        return true;
    }

}

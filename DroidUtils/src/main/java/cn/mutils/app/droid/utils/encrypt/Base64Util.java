package cn.mutils.app.droid.utils.encrypt;

import android.util.Base64;

/**
 * Base64工具类
 */
public class Base64Util {

    private static final String CHARSET = "UTF-8";

    private Base64Util() {

    }

    /**
     * 将base64编码的字符串解码
     *
     * @param base64 已编码的字符串
     * @return 二进制数据
     */
    public static byte[] toBytes(String base64) {
        try {
            return Base64.decode(base64.getBytes(CHARSET), Base64.NO_WRAP);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 将二进制编码为base64字符串
     *
     * @param bytes 二进制数据
     * @return base64编码
     */
    public static String fromBytes(byte[] bytes) {
        try {
            return new String(Base64.encode(bytes, Base64.NO_WRAP), CHARSET);
        } catch (Exception e) {
            return null;
        }
    }

}

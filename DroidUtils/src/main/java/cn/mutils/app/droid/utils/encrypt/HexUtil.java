package cn.mutils.app.droid.utils.encrypt;

/**
 * 十六进制实用类
 */
public class HexUtil {

    /**
     * 十六进制数组
     */
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'a', 'b', 'c', 'd', 'e', 'f'};

    private HexUtil() {

    }

    /**
     * 将二进制转换为十六进制字符串
     *
     * @param data 二进制数据
     * @return 大写格式十六进制字符串
     */
    public static String toHex(byte[] data) {
        return toHex(data, false);
    }

    /**
     * 将二进制转换为十六进制字符串
     *
     * @param data            二进制数据
     * @param lowerCaseFormat 是否是小写格式
     * @return 十六进制字符串
     */
    public static String toHex(byte[] data, boolean lowerCaseFormat) {
        if (data == null) {
            return null;
        }
        char[] str = new char[data.length + data.length];
        for (int i = data.length - 1, strIndex = str.length - 1; i >= 0; i--) {
            byte byte4i = data[i];
            int hexIndex = byte4i & 0xF;
            if (lowerCaseFormat) {
                hexIndex = hexIndex < 10 ? hexIndex : hexIndex + 6;
            }
            str[strIndex--] = HEX_DIGITS[hexIndex];
            hexIndex = byte4i >>> 4 & 0xF;
            if (lowerCaseFormat) {
                hexIndex = hexIndex < 10 ? hexIndex : hexIndex + 6;
            }
            str[strIndex--] = HEX_DIGITS[hexIndex];
        }
        return new String(str);
    }

}

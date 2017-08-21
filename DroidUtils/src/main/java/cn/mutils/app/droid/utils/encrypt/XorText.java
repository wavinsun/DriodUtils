package cn.mutils.app.droid.utils.encrypt;

/**
 * 异或加密解密
 * <p>
 * Created by wenhua.ywh on 2017/8/21.
 */
public class XorText {

    private static final char[] KEY = {'\u5807', '\u82B1'};

    /**
     * 编码
     *
     * @param plainText 明文
     * @return 密文
     */
    public static String encode(String plainText) {
        return xor(plainText);
    }

    /**
     * 解码
     *
     * @param encodedText 密文
     * @return 明文
     */
    public static String decode(String encodedText) {
        return xor(encodedText);
    }

    private static String xor(String text) {
        if (text == null) {
            return null;
        }
        try {
            char[] chars = text.toCharArray();
            boolean flag = true;
            char odd = KEY[0];
            char even = KEY[1];
            for (int i = 0, size = chars.length; i < size; i++) {
                chars[i] = (char) (chars[i] ^ (flag ? odd : even));
                flag = !flag;
            }
            return new String(chars);
        } catch (Exception e) {
            return null;
        }
    }

}

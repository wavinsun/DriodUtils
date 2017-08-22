package cn.mutils.app.droid.utils.encrypt;

/**
 * 异或加密解密
 * <p>
 * Created by wenhua.ywh on 2017/8/21.
 */
public class XorText {

    private static final char KEY_DATA_ODD = '\u5807';
    private static final char KEY_DATA_EVEN = '\u82B1';

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
        char[] chars = text.toCharArray();
        boolean isOdd = true;
        for (int i = 0, size = chars.length; i < size; i++) {
            chars[i] = (char) (chars[i] ^ (isOdd ? KEY_DATA_ODD : KEY_DATA_EVEN));
            isOdd = !isOdd;
        }
        return new String(chars);
    }

}

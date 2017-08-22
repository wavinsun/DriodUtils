package cn.mutils.app.droid.utils.encrypt;

/**
 * 异或魔法文本
 * <p>
 * Created by wenhua.ywh on 2017/8/21.
 */
public class MagicXorText {

    private static final char KEY_DATA_ODD = '\u5807';
    private static final char KEY_DATA_EVEN = '\u82B1';
    private static final char KEY_HEAD = '\uAD5C';

    /**
     * 编码
     *
     * @param plainText 明文
     * @return 密文
     */
    public static String encode(String plainText) {
        if (plainText == null) {
            return null;
        }
        int plainTextSize = plainText.length();
        char[] encoded = new char[plainTextSize + 1];
        encoded[0] = (char) (KEY_HEAD ^ plainTextSize);
        if (plainTextSize != 0) {
            plainText.getChars(0, plainTextSize, encoded, 1);
            xor(encoded, 1, plainTextSize);
        }
        return new String(encoded);
    }

    /**
     * 解码
     *
     * @param encodedText 密文
     * @return 明文
     */
    public static String decode(String encodedText) {
        if (encodedText == null) {
            return null;
        }
        char[] encoded = encodedText.toCharArray();
        int encodedSize = encoded.length;
        if (encodedSize == 0) {
            return encodedText;
        }
        int plainTextSize = encodedSize - 1;
        if (encoded[0] != ((char) (KEY_HEAD ^ plainTextSize))) {
            return encodedText;
        }
        if (plainTextSize != 0) {
            xor(encoded, 1, plainTextSize);
        }
        return new String(encoded, 1, plainTextSize);
    }

    private static void xor(char[] chars, int offset, int count) {
        if (chars == null) {
            return;
        }
        if (offset < 0 || count < 0 || (offset > chars.length - count)) {
            return;
        }
        boolean isOdd = true;
        for (int i = offset, to = offset + count; i < to; i++) {
            chars[i] = (char) (chars[i] ^ (isOdd ? KEY_DATA_ODD : KEY_DATA_EVEN));
            isOdd = !isOdd;
        }
    }

}

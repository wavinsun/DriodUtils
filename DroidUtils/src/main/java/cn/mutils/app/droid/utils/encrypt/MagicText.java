package cn.mutils.app.droid.utils.encrypt;

/**
 * 魔法文本
 * <p>
 * Created by wenhua.ywh on 2017/8/17.
 */
public class MagicText {

    private static final String MAGIC_NUMBER = "5aCH6Iqx"; //魔数
    private static final int HEAD_EACH_UNIT_SIZE = 8; //头单元大小
    private static final String HEAD_UNIT_ZERO = "00000000"; //默认为零的头
    private static final int HEAD_UNIT_COUNT = 4; // 头单元个数 第一个是魔数，第二个是版本号，第三个是数据段长度，第四个是数据段首尾之和

    /**
     * 加密文本
     *
     * @param plainText 明文
     * @return 密文
     */
    public static String encryptText(String plainText) {
        if (plainText == null) {
            return null;
        }
        String dataString = encodeString(plainText);
        return makeHeadString(dataString);
    }

    /**
     * 解密文本
     *
     * @param encryptedText 密文
     * @return 明文
     */
    public static String decryptText(String encryptedText) {
        if (encryptedText == null) {
            return null;
        }
        if (!isValidHead(encryptedText)) {
            return encryptedText;
        }
        return decodeString(encryptedText.substring(32));
    }

    /**
     * 判断是否是密文格式
     *
     * @param text 文本
     * @return 是否是密文
     */
    public static boolean isEncryptedText(String text) {
        return isValidHead(text);
    }

    /**
     * 获取明文
     *
     * @param text 文本
     * @return 明文
     */
    public static String getPlainText(String text) {
        if (text == null) {
            return null;
        }
        if (!isValidHead(text)) {
            return text;
        }
        return decodeString(text.substring(32));
    }

    /**
     * 获取密文
     *
     * @param text 文本
     * @return 密文
     */
    public static String getEncryptedText(String text) {
        if (text == null) {
            return null;
        }
        if (isValidHead(text)) {
            return text;
        }
        String dataString = encodeString(text);
        return makeHeadString(dataString);
    }

    private static String makeUnitString(String original) {
        if (original == null) {
            return HEAD_UNIT_ZERO;
        }
        int originalSize = original.length();
        if (originalSize == HEAD_EACH_UNIT_SIZE) {
            return original;
        } else if (originalSize < HEAD_EACH_UNIT_SIZE) {
            String bigStr = HEAD_UNIT_ZERO + original;
            return bigStr.substring(bigStr.length() - HEAD_EACH_UNIT_SIZE);
        } else {
            return original.substring(originalSize - HEAD_EACH_UNIT_SIZE);
        }
    }

    private static boolean isValidHead(String text) {
        if (text == null) {
            return false;
        }
        int textSize = text.length();
        if (textSize < 32) {
            return false;
        }
        String magic = text.substring(0, 8);
        if (!magic.equals(MAGIC_NUMBER)) {
            return false;
        }
        String version = text.substring(8, 16);
        try {
            int versionValue = Integer.parseInt(version, 16);
            if (versionValue != 818) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        String dataSize = text.substring(16, 24);
        try {
            int dataSizeValue = Integer.parseInt(dataSize, 16);
            if (dataSizeValue != textSize - 32) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        String checkSum = text.substring(24, 32);
        int checkValue = textSize > 32 ? (text.charAt(32) + text.charAt(textSize - 1)) : 0;
        try {
            int checkSumValue = Integer.parseInt(checkSum, 16);
            if (checkSumValue != checkValue) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    private static String makeHeadString(String data) {
        if (data == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(MAGIC_NUMBER);
        sb.append(makeUnitString(Integer.toHexString(818)));
        int dataSize = data.length();
        sb.append(makeUnitString(Integer.toHexString(dataSize)));
        int checkValue = dataSize > 0 ? (data.charAt(0) + data.charAt(dataSize - 1)) : 0;
        sb.append(makeUnitString(Integer.toHexString(checkValue)));
        sb.append(data);
        return sb.toString();
    }

    private static String encodeString(String plainData) {
        return XorText.encode(plainData);
    }

    private static String decodeString(String encodedData) {
        return XorText.decode(encodedData);
    }

}

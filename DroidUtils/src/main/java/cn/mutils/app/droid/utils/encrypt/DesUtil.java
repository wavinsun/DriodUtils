package cn.mutils.app.droid.utils.encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * DES工具类
 */
public class DesUtil {

    private DesUtil() {

    }

    /**
     * 加密
     *
     * @param plainText 明文
     * @param key       密钥
     * @return 密文
     */
    public static String encrypt(String plainText, String key) {
        try {
            Cipher cipher = createCipher(Cipher.ENCRYPT_MODE, key);
            byte[] data = cipher.doFinal(plainText.getBytes("UTF-8"));
            return Base64Util.fromBytes(data);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 解密
     *
     * @param cipherText 密文
     * @param key        密码
     * @return 明文
     */
    public static String decrypt(String cipherText, String key) {
        try {
            byte[] data = Base64Util.toBytes(cipherText);
            Cipher cipher = createCipher(Cipher.DECRYPT_MODE, key);
            byte[] plainData = cipher.doFinal(data);
            return new String(plainData, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    private static Cipher createCipher(int mode, String key) throws Exception {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("DES");
        SecretKey secretKey = factory.generateSecret(new DESKeySpec(key.getBytes("UTF-8")));
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(mode, secretKey, new SecureRandom());
        return cipher;
    }

}

package cn.mutils.app.droid.utils.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import cn.mutils.app.droid.utils.io.FileUtil;
import cn.mutils.app.droid.utils.io.IOUtil;

/**
 * SHA1WithRSA签名校验工具
 */
public class RsaUtil {

    private static final String SIGN_ALGORITHM = "SHA1WithRSA";
    private static final String KEY_ALGORITHM = "RSA";

    private RsaUtil() {

    }

    /**
     * 使用公钥校验文件签名是否有效
     *
     * @param file         文件
     * @param sign         bas64编码格式的签名
     * @param publicKeyStr base64编码格式的公钥
     * @return 是否有效
     */
    public static boolean verify(File file, String sign, String publicKeyStr) {
        if (file == null || (sign == null || sign.length() == 0) || (publicKeyStr == null || publicKeyStr.length() == 0)) {
            return false;
        }
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            byte[] keyBytes = Base64Util.toBytes(publicKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
            PublicKey publicKey = keyFactory.generatePublic(keySpec);
            Signature signature = Signature.getInstance(SIGN_ALGORITHM);
            signature.initVerify(publicKey);
            int count = -1;
            byte[] buffer = new byte[1024];
            while ((count = fis.read(buffer)) != -1) {
                signature.update(buffer, 0, count);
            }
            byte[] signByte = Base64Util.toBytes(sign);
            return signature.verify(signByte);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtil.closeQuietly(fis);
        }
    }

    /**
     * 使用私钥对文件进行签名
     *
     * @param file          文件
     * @param privateKeyStr base64编码的私钥
     * @return base64编码格式的签名
     */
    public static String sign(File file, String privateKeyStr) {
        FileInputStream fis = null;
        try {
            byte[] keyBytes = Base64Util.toBytes(privateKeyStr);
            KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
            Signature signature = Signature.getInstance(SIGN_ALGORITHM);
            signature.initSign(privateKey);
            int count = -1;
            byte[] buffer = new byte[1024];
            fis = new FileInputStream(file);
            while ((count = fis.read(buffer)) != -1) {
                signature.update(buffer, 0, count);
            }
            byte[] signBytes = signature.sign();
            return Base64Util.fromBytes(signBytes);
        } catch (Exception e) {
            return null;
        } finally {
            IOUtil.closeQuietly(fis);
        }
    }

    /**
     * 自动生成成对公钥与私钥
     *
     * @param publicKeyFile  公钥文件
     * @param privateKeyFile 私钥文件
     * @return 死否成功
     */
    public static boolean generateKeyPair(File publicKeyFile, File privateKeyFile) {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
            keyPairGen.initialize(1024, new SecureRandom());
            KeyPair keyPair = keyPairGen.generateKeyPair();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            String publicKeyString = Base64Util.fromBytes(publicKey.getEncoded());
            String privateKeyString = Base64Util.fromBytes(privateKey.getEncoded());
            if (!FileUtil.writeString(publicKeyFile, publicKeyString)) {
                return false;
            }
            if (!FileUtil.writeString(privateKeyFile, privateKeyString)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}

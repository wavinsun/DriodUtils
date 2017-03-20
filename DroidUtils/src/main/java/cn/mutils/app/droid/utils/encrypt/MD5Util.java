package cn.mutils.app.droid.utils.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

import cn.mutils.app.droid.utils.io.IOUtil;

/**
 * MD5实用类
 *
 * 默认返回大写字母形式，如果调用过程中发生异常返回空字符串
 */
public class MD5Util {

    private MD5Util() {

    }

    /**
     * 获取文件MD5
     *
     * @param file 文件
     * @return 大写格式摘要
     */
    public static String getFileMD5(File file) {
        return getFileMD5(file, "", false);
    }

    /**
     * 获取文件MD5
     *
     * @param file            文件
     * @param defValue        默认值
     * @param lowerCaseFormat 是否是小写格式
     * @return 摘要
     */
    public static String getFileMD5(File file, String defValue, boolean lowerCaseFormat) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            return getStreamMD5(fis, lowerCaseFormat);
        } catch (Exception e) {
            return defValue;
        } finally {
            IOUtil.closeQuietly(fis);
        }
    }

    /**
     * 获取二进制MD5
     *
     * @param data 数据
     * @return 大写格式摘要
     */
    public static String getByteArrayMD5(byte[] data) {
        return getByteArrayMD5(data, "", false);
    }

    /**
     * 获取二进制MD5
     *
     * @param data            数据
     * @param defValue        默认值
     * @param lowerCaseFormat 是否是小写格式
     * @return 摘要
     */
    public static String getByteArrayMD5(byte[] data, String defValue, boolean lowerCaseFormat) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(data);
            return HexUtil.toHex(md5.digest(), lowerCaseFormat);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * 获取数据流MD5
     *
     * @param input           数据流
     * @param lowerCaseFormat 是否是小写格式
     * @return 摘要
     */
    public static String getStreamMD5(InputStream input, boolean lowerCaseFormat) throws Exception {
        return HexUtil.toHex(getStreamMD5Digest(input), lowerCaseFormat);
    }

    /**
     * 获取数据流摘要
     */
    public static byte[] getStreamMD5Digest(InputStream input) throws Exception {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] buffer = new byte[IOUtil.DEFAULT_BUFFER_SIZE];
        int count = -1;
        while ((count = input.read(buffer)) != -1) {
            md5.update(buffer, 0, count);
        }
        return md5.digest();
    }

    /**
     * 获取字符串MD5
     *
     * @param data 字符串
     * @return 大写格式摘要
     */
    public static String getStringMD5(String data) {
        return getStringMD5(data, "", false);
    }

    /**
     * 获取字符串MD5
     *
     * @param data            字符串
     * @param defValue        默认值
     * @param lowerCaseFormat 是否是小写格式
     * @return 摘要
     */
    public static String getStringMD5(String data, String defValue, boolean lowerCaseFormat) {
        try {
            return getByteArrayMD5(data.getBytes("UTF-8"), defValue, lowerCaseFormat);
        } catch (Exception e) {
            return defValue;
        }
    }

    /**
     * 获取数据MD5，如果数据长度超出最大长度则取最大长度之前的二进制数据的MD5
     *
     * @param data    数据
     * @param maxSize 最大数据长度
     * @return 大写格式摘要
     */
    public static String getByteArrayMD5(byte[] data, int maxSize) {
        return getByteArrayMD5(data, maxSize, "", false);
    }

    /**
     * 获取数据MD5，如果数据长度超出最大长度则取最大长度之前的二进制数据的MD5
     *
     * @param data            数据
     * @param maxSize         最大数据长度
     * @param defValue        默认值
     * @param lowerCaseFormat 是否是小写格式
     * @return 摘要
     */
    public static String getByteArrayMD5(byte[] data, int maxSize, String defValue, boolean lowerCaseFormat) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            if (data.length > maxSize) {
                md5.update(data, 0, maxSize);
            } else {
                md5.update(data);
            }
            return HexUtil.toHex(md5.digest());
        } catch (Exception e) {
            return defValue;
        }
    }

}

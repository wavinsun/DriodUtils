package cn.mutils.app.droid.utils.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * Zip压缩工具
 */
public class ZipUtil {

    private ZipUtil() {

    }

    /**
     * 将文件数组写入压缩包
     * @param zipFile 压缩包文件
     * @param files 文件数组
     * @return 是否成功
     */
    public static boolean writeFilesToZip(File zipFile, List<File> files) {
        ZipOutputStream out = null;
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(zipFile);
            CheckedOutputStream cos = new CheckedOutputStream(fos, new CRC32());
            out = new ZipOutputStream(cos);
            for (File file : files) {
                BufferedInputStream bis = null;
                try {
                    bis = new BufferedInputStream(new FileInputStream(file));
                    ZipEntry entry = new ZipEntry(file.getName());
                    out.putNextEntry(entry);
                    IOUtil.copy(bis, out);
                } catch (Exception e) {
                    return false;
                } finally {
                    IOUtil.closeQuietly(bis);
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            IOUtil.closeQuietly(out);
            IOUtil.closeQuietly(fos);
        }
    }

    /**
     * 将zip文件解压到指定目录
     * @param zipFile zip文件
     * @param dir 解压目录
     * @return 是否成功
     */
    public static boolean unzipToDir(File zipFile, File dir) {
        if (!zipFile.isFile()) {
            return false;
        }
        if (!dir.exists()) {
            dir.mkdirs();
        }
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(new FileInputStream(zipFile));
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null && !entry.isDirectory()) {
                File target = new File(dir, entry.getName());
                if (!target.getParentFile().exists()) {
                    target.getParentFile().mkdirs();
                }
                BufferedOutputStream bos = null;
                try {
                    bos = new BufferedOutputStream(new FileOutputStream(target));
                    IOUtil.copy(zis, bos);
                    bos.flush();
                } catch (Exception e) {
                    return false;
                } finally {
                    IOUtil.closeQuietly(bos);
                }
            }
            zis.closeEntry();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            IOUtil.closeQuietly(zis);
        }
    }

}

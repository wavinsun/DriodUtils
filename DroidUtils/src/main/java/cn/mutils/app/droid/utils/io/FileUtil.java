package cn.mutils.app.droid.utils.io;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.StatFs;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.util.List;

import cn.mutils.app.droid.utils.encrypt.MD5Util;
import cn.mutils.app.droid.utils.os.ThreadExecutor;

/**
 * 文件工具类
 */
public class FileUtil {

    private FileUtil() {

    }

    /**
     * 根据图片的资源id，生成图片文件
     *
     * @param context  上下文
     * @param resId    要打开的资源id
     * @param destFile 生成的目标文件
     */
    public static void copyRawFile(Context context, int resId, File destFile) {
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = context.getResources().openRawResource(resId);
            fos = new FileOutputStream(destFile);
            IOUtil.copy(is, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(is);
            IOUtil.closeQuietly(fos);
        }
    }

    /**
     * 判断文件是否被锁
     *
     * @param file 文件
     */
    public static boolean isFileLocked(String file) {
        RandomAccessFile raf = null;
        FileChannel channel = null;
        FileLock lock = null;
        try {
            raf = new RandomAccessFile(file, "rw");
            channel = raf.getChannel();
            lock = channel.tryLock();
            if (lock != null) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (lock != null) {
                    lock.release();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            IOUtil.closeQuietly(channel);
            IOUtil.closeQuietly(raf);
        }
        return true;
    }

    /**
     * 检测文件是否存在
     *
     * @param fileName 文件
     * @return boolean
     */
    public static boolean isFileExists(String fileName) {
        if (!TextUtils.isEmpty(fileName)) {
            return new File(fileName).exists();
        } else {
            return false;
        }
    }

    /**
     * 循环删除文件及文件夹
     *
     * @param file 文件或者文件夹
     */
    public static boolean deleteFile(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0, len = files.length; i < len; i++) {
                if (!deleteFile(files[i])) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    /**
     * 获取asset下的资源文件数据
     *
     * @param context   上下文
     * @param assetName 资源文件名
     */
    public static byte[] getAssetFileData(Context context, String assetName) {
        AssetManager assetManager = context.getAssets();
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = assetManager.open(assetName);
            bos = new ByteArrayOutputStream();
            IOUtil.copy(is, bos);
            return bos.toByteArray();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtil.closeQuietly(is);
            IOUtil.closeQuietly(bos);
        }
    }

    /**
     * 拷贝asset资源文件
     *
     * @param context   上下文
     * @param assetName asset资源名称
     * @param destFile  目标文件
     * @return 是否拷贝成功
     */
    public static boolean copyAssetFile(Context context, String assetName, File destFile) {
        AssetManager assetManager = context.getAssets();
        InputStream is = null;
        FileOutputStream fos = null;
        try {
            is = assetManager.open(assetName);
            fos = new FileOutputStream(destFile);
            IOUtil.copy(is, fos);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtil.closeQuietly(is);
            IOUtil.closeQuietly(fos);
        }
    }

    /**
     * 将byte数据保存到文件
     *
     * @param fileName 文件名
     * @param data     数据
     * @return 是否写入成功
     */
    public static boolean writeDataToFile(String fileName, byte[] data) {
        OutputStream os = null;
        try {
            if (data == null || data.length == 0) {
                return true;
            }
            File file = new File(fileName);
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }
            os = new FileOutputStream(file);
            os.write(data);
            os.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            IOUtil.closeQuietly(os);
        }
    }

    /**
     * 获取文件数据
     *
     * @param fileName 文件名
     */
    public static byte[] getFileData(String fileName) {
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        try {
            is = new FileInputStream(fileName);
            bos = new ByteArrayOutputStream();
            IOUtil.copy(is, bos);
            return bos.toByteArray();
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtil.closeQuietly(is);
            IOUtil.closeQuietly(bos);
        }
    }

    /**
     * 打开文件输入流
     *
     * @param file 对应的文件
     * @return 文件对应的输出入流
     * @throws IOException 文件为目录、没有写权限、创建上级目录失败等
     */
    public static FileInputStream openInputStream(@NonNull File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canRead() == false) {
                throw new IOException("File '" + file + "' cannot be read");
            }
        } else {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        }
        return new FileInputStream(file);
    }


    /**
     * 打开文件输出流
     *
     * @param file 对应的文件
     * @return 文件对应的输出流
     * @throws IOException 文件为目录、没有写权限、创建上级目录失败等
     */
    public static FileOutputStream openOutputStream(@NonNull File file) throws IOException {
        return openOutputStream(file, false);
    }

    /**
     * 以追加方式打开文件输出流
     *
     * @param file   对应的文件
     * @param append true：以追加方式打开，相关bytes会被追加到文件最后，false：反之
     * @return 文件对应的输出流
     * @throws IOException 文件为目录、没有写权限、创建上级目录失败等
     */
    public static FileOutputStream openOutputStream(@NonNull File file, boolean append) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                throw new IOException("File '" + file + "' exists but is a directory");
            }
            if (file.canWrite() == false) {
                throw new IOException("File '" + file + "' cannot be written to");
            }
        } else {
            File parent = file.getParentFile();
            if (parent != null) {
                if (!parent.mkdirs() && !parent.isDirectory()) {
                    throw new IOException("Directory '" + parent + "' could not be created");
                }
            }
        }
        return new FileOutputStream(file, append);
    }


    /**
     * 重命名文件
     *
     * @param sourceFile 源文件
     * @param targetFile 目标文件
     */
    public static void rename(String sourceFile, String targetFile) {
        if (TextUtils.isEmpty(sourceFile) || TextUtils.isEmpty(targetFile)) {
            return;
        }
        File source = new File(sourceFile);
        File target = new File(targetFile);
        if (target.exists()) {
            deleteFile(target);
        }
        source.renameTo(target);
    }

    /**
     * 安装文件
     *
     * @param context 上下文
     * @param apk     apk文件
     */
    public static boolean installFile(Context context, File apk) {
        if (context == null || apk == null) {
            return false;
        }
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(apk), "application/vnd.android.package-archive");
        try {
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 放开文件的读写运行权限
     *
     * @param file 文件
     */
    public static int permissionAll(File file) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec("chmod 777 " + file);
            int status = p.waitFor();
            return status;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (p != null) {
                p.destroy();
            }
        }
        return -1;
    }

    /**
     * 拷贝文件
     *
     * @param src  源文件
     * @param dest 目标文件
     * @return 是否拷贝成功
     */
    public static boolean copyFile(File src, File dest) {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            String files[] = src.list();
            if (null != files) {
                for (String file : files) {
                    File srcFile = new File(src, file);
                    File destFile = new File(dest, file);
                    if (!copyFile(srcFile, destFile)) {
                        return false;
                    }
                }
                return true;
            }
            return false;
        } else {
            if (dest.exists()) {
                dest.delete();
            }
            InputStream in = null;
            OutputStream out = null;
            try {
                in = new FileInputStream(src);
                out = new FileOutputStream(dest);
                IOUtil.copy(in, out);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                IOUtil.closeQuietly(in);
                IOUtil.closeQuietly(out);
            }
            String md5Src = MD5Util.getFileMD5(src);
            String md5Dest = MD5Util.getFileMD5(dest);
            if (!md5Src.isEmpty() && !md5Dest.equals(md5Src)) {
                return false;
            }
            return true;
        }
    }

    /**
     * 拷贝文件夹
     *
     * @param src  源文件夹
     * @param dest 目标文件夹
     * @return 是否拷贝成功
     */
    public static boolean copyFolder(File src, File dest) {
        return copyFile(src, dest);
    }

    /**
     * 删除文件夹
     *
     * @param src 文件夹
     */
    public static boolean deleteFolder(File src) {
        return deleteFile(src);
    }

    /**
     * 获取文件夹大小
     *
     * @param folder 文件夹
     */
    public static long getFolderSize(File folder) {
        long totalSize = 0;
        if (folder.isDirectory()) {
            String files[] = folder.list();
            if (null != files) {
                for (String file : files) {
                    File srcFile = new File(folder, file);
                    totalSize += getFolderSize(srcFile);
                }
            }
        } else {
            totalSize += folder.length();
        }
        return totalSize;
    }

    /**
     * 获取文件扩展名
     *
     * @param fileName 文件名
     * @return 扩展名
     */
    public static String getExtension(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return "";
        }
        int lastDotPosition = fileName.lastIndexOf(".");
        if (lastDotPosition == -1) {
            return "";
        }
        return fileName.substring(lastDotPosition + 1);
    }

    /**
     * 获取当前路径path的上级目录.
     *
     * @param path 当前路径
     * @return path的上级目录 e.x. hello/hello => hello/
     */
    public static String getParentPath(@Nullable final String path) {
        if (TextUtils.isEmpty(path)) {
            return "";
        }
        int separatorIndex = -1;
        for (int i = path.length() - 1; i >= 0; --i) {
            if (path.charAt(i) == File.separatorChar && i != path.length() - 1) {
                separatorIndex = i;
                break;
            }
        }
        if (separatorIndex > -1) {
            return path.substring(0, separatorIndex + 1);
        }
        return "";
    }

    /**
     * 异步删除文件
     *
     * @param paths 问价列表
     */
    public static void cleanUpFilesAsync(final List<String> paths) {
        ThreadExecutor.post(new Runnable() {
            @Override
            public void run() {
                for (String path : paths) {
                    if (path == null) {
                        continue;
                    }
                    try {
                        deleteFile(new File(path));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * 获取一个用于打开文件的intent
     *
     * @param path 路径
     */
    public static Intent getAllIntent(String path) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "*/*");
        return intent;
    }

    /**
     * 获取一个用于打开APK文件的intent
     *
     * @param path 路径
     */
    public static Intent getApkFileIntent(String path) {
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction(Intent.ACTION_VIEW);
        Uri uri = Uri.fromFile(new File(path));
        intent.setDataAndType(uri, "application/vnd.android.package-archive");
        return intent;
    }

    /**
     * 从文件读取字符串
     *
     * @param file     对应文件
     * @param encoding 编码格式、传null是使用<code>Charset.defaultCharset()</code>
     * @return 文件对应的内容
     */
    public static String readFileToString(@NonNull File file, @NonNull Charset encoding) {
        InputStream in = null;
        try {
            in = openInputStream(file);
            return IOUtil.readString(in, encoding);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(in);
        }
        return "";
    }

    /**
     * 从文件读取字符串
     *
     * @param file     对应文件
     * @param encoding 编码格式、传null是使用<code>Charset.defaultCharset()</code>
     * @return 文件对应的内容
     */
    public static String readFileToString(@NonNull File file, @Nullable String encoding) {
        InputStream in = null;
        try {
            in = openInputStream(file);
            return IOUtil.readString(in, encoding);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(in);
        }
        return "";
    }


    /**
     * 从文件读取字符串,使用默认编码格式
     *
     * @param file 对应文件
     * @return 文件对应的内容
     */
    public static String readFileToString(File file) {
        return readFileToString(file, Charset.defaultCharset());
    }

    /**
     * 读取assert文件夹中文件到字符串
     *
     * @param context  上下文，用户获取<code>AssetManager</code>
     * @param fileName 文件名称
     * @param charset  编码格式
     * @return 内容
     */
    public static String readAssertFileToString(@NonNull Context context, @NonNull String fileName, Charset charset) {
        if (TextUtils.isEmpty(fileName)) return "";
        AssetManager assetManager = context.getAssets();
        InputStream inputStream = null;
        try {
            inputStream = assetManager.open(fileName);
            return IOUtil.readString(inputStream, charset);
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(inputStream);
        }
        return "";
    }

    /**
     * 读取assert文件夹中文件到字符串,使用默认编码
     *
     * @param context  上下文，用户获取<code>AssetManager</code>
     * @param fileName 文件名称
     * @return 内容
     */
    public static String readAssertFileToString(@NonNull Context context, @NonNull String fileName) {
        return readAssertFileToString(context, fileName, Charset.defaultCharset());
    }

    /**
     * 将字符串写入文件
     *
     * @param file     需要写入的文件
     * @param data     对应字符串
     * @param encoding 字符串编码方式,传null时使用系统默认
     * @return true:写入成功
     */
    public static boolean writeStringToFile(@NonNull File file, @Nullable String data, @NonNull Charset encoding) {
        return writeStringToFile(file, data, encoding, false);
    }

    /**
     * 将字符串写入文件
     *
     * @param file     需要写入的文件
     * @param data     对应字符串
     * @param encoding 字符串编码方式,传null时使用系统默认
     * @return true:写入成功
     */
    public static boolean writeStringToFile(@NonNull File file, @Nullable String data, @Nullable String encoding) {
        return writeStringToFile(file, data, encoding, false);
    }

    /**
     * 将字符串写入文件
     *
     * @param file     需要写入的文件
     * @param data     对应字符串
     * @param encoding 字符串编码方式,传null时使用系统默认
     * @param append   true：以追加方式写入;false:覆盖
     * @return true:写入成功
     */
    public static boolean writeStringToFile(@NonNull File file, @Nullable String data, @Nullable Charset encoding, boolean append) {
        OutputStream out = null;
        boolean success = false;
        try {
            out = openOutputStream(file, append);
            IOUtil.writeString(data, out, encoding);
            success = true;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(out);
        }
        return success;
    }

    /**
     * 将字符串写入文件
     *
     * @param file     需要写入的文件
     * @param data     对应字符串
     * @param encoding 字符串编码方式,传null时使用系统默认
     * @param append   true：以追加方式写入;false:覆盖
     * @return true:写入成功
     */
    public static boolean writeStringToFile(@NonNull String file, @Nullable String data, @Nullable Charset encoding, boolean append) {
        OutputStream out = null;
        boolean success = false;
        try {
            out = openOutputStream(new File(file), append);
            IOUtil.writeString(data, out, encoding);
            success = true;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(out);
        }
        return success;
    }

    /**
     * 将字符串写入文件
     *
     * @param file     需要写入的文件
     * @param data     对应字符串
     * @param encoding 字符串编码方式,传null时使用系统默认
     * @param append   true：以追加方式写入;false:覆盖
     * @return true:写入成功
     */
    public static boolean writeStringToFile(@NonNull File file, @Nullable String data, @Nullable String encoding, boolean append) {
        OutputStream out = null;
        boolean success = false;
        try {
            out = openOutputStream(file, append);
            IOUtil.writeString(data, out, encoding);
            success = true;
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            IOUtil.closeQuietly(out);
        }
        return success;
    }

    /**
     * 将字符串写入文件，使用系统默认编码
     *
     * @param file 需要写入的文件
     * @param data 对应字符串
     * @return true:写入成功
     */
    public static boolean writeStringToFile(@NonNull File file, @Nullable String data) {
        return writeStringToFile(file, data, Charset.defaultCharset(), false);
    }


    /**
     * 将字符串写入文件，使用系统默认编码
     *
     * @param file   需要写入的文件
     * @param data   对应字符串
     * @param append true：追加方式写入
     * @return true:写入成功
     */
    public static boolean writeStringToFile(@NonNull File file, @Nullable String data, boolean append) {
        return writeStringToFile(file, data, Charset.defaultCharset(), append);
    }

    /**
     * 获取可用的存储空间
     *
     * @return 返回long的数组 下标0为剩余空间 下标1为已用空间 下标2为总空间
     */
    public static long[] getSpaceSizes(String path) {
        long[] space = new long[3];
        try {
            StatFs statFs = new StatFs(path);
            long blockSize = statFs.getBlockSize();
            long availableBlocks = statFs.getAvailableBlocks();
            long blockCount = statFs.getBlockCount();
            space[0] = availableBlocks * blockSize;
            space[1] = (blockCount - availableBlocks) * blockSize;
            space[2] = blockCount * blockSize;
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return space;
    }

    /**
     * 获取文件内容
     *
     * @param file 文件
     * @return 内容
     */
    public static String getString(File file) {
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream();
            IOUtil.copy(fis, bos);
            return bos.toString("UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            IOUtil.closeQuietly(bos);
            IOUtil.closeQuietly(fis);
        }
    }

    /**
     * 将字符串写入文件
     *
     * @param file   文件
     * @param string 字符串
     * @return 是否成功
     */
    public static boolean writeString(File file, String string) {
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            byte[] bytes = string.getBytes("UTF-8");
            fos.write(bytes);
            fos.flush();
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            IOUtil.closeQuietly(fos);
        }
    }

}

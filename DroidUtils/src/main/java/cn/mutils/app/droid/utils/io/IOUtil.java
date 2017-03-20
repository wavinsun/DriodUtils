package cn.mutils.app.droid.utils.io;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;

/**
 * I/O操作工具类
 *
 * 流的关闭、读写、复制等
 */
public class IOUtil {

    public static final int EOF = -1;
    public static final int DEFAULT_BUFFER_SIZE = 4096; // 4K

    private IOUtil() {

    }

    /**
     * 无条件关闭可关闭对象
     *
     * @param closeable 需要关闭的对象
     */
    public static void closeQuietly(@Nullable Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable ignored) {
                // ignored
            }
        }
    }

    /**
     * 判断输入流是否为以指定<code>byte[]</code>开始
     *
     * @param input 输入流
     * @param data  指定<code>byte[]</code>
     * @return 返回true如果以指定数组开始
     * @throws IOException 出现IO异常
     */
    public static boolean startsWith(@NonNull InputStream input, byte[] data) throws IOException {
        for (byte b : data) {
            if (input.read() != (b & 0xFF)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输入流转bytes
     *
     * @param input 输入流
     * @return 对应<code>byte[]</code>
     * @throws IOException 出现IO异常
     */
    public static byte[] read(@NonNull InputStream input) throws IOException {
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            copy(input, out);
            return out.toByteArray();
        } finally {
            closeQuietly(out);
        }
    }

    /**
     * 将bytes数据写入对应输出流
     *
     * @param data   需要写入的bytes，写入期间不要对<code>byte[]</code>做操作
     * @param output 输出流
     * @throws IOException 出现IO异常
     */
    public static void write(@Nullable byte[] data, @NonNull OutputStream output)
            throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

    /**
     * 复制输入流bytes数据到输出流
     *
     * @param input  输入流
     * @param output 输出流
     * @return 返回实际复制数据长度
     * @throws IOException 出现IO异常
     */
    public static int copy(@NonNull InputStream input, @NonNull OutputStream output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    /**
     * 复制输入chars数据到输出流
     *
     * @param input  输入
     * @param output 输出
     * @return 返回实际输入长度
     * @throws IOException 出现IO异常
     */
    public static int copy(@NonNull Reader input, @NonNull Writer output) throws IOException {
        long count = copyLarge(input, output);
        if (count > Integer.MAX_VALUE) {
            return -1;
        }
        return (int) count;
    }

    /**
     * 复制bytes数据，支持超过2GB数据流 <p/> 内部创建缓冲区，因此不需要使用 <code>BufferedInputStream</code>.
     *
     * @param input  输入流
     * @param output 输出流
     * @return 实际复制的数据长度
     * @throws IOException 出现IO异常
     */
    public static long copyLarge(@NonNull InputStream input, @NonNull OutputStream output)
            throws IOException {
        return copyLarge(input, output, new byte[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 复制bytes数据，支持超过2GB数据流 <p/> 使用提供的缓冲区，因此不需要使用 <code>BufferedInputStream</code>.
     *
     * @param input  输入流
     * @param output 输出流
     * @param buffer 复制使用的缓冲区
     * @return 实际复制的数据长度
     * @throws IOException 出现IO异常
     */
    public static long copyLarge(@NonNull InputStream input, @NonNull OutputStream output, @NonNull byte[] buffer)
            throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    public static char[] read(Reader input) throws IOException {
        CharArrayWriter out = null;
        try {
            out = new CharArrayWriter();
            copy(input, out);
            return out.toCharArray();
        } finally {
            closeQuietly(out);
        }
    }

    /**
     * 复制chars数据，支持超过2GB数据 <p/> 内部会创建缓冲区，因此不需要使用 <code>BufferedReader</code>.
     *
     * @param input  输入
     * @param output 输出
     * @return 实际复制数据长度
     * @throws IOException 出现IO异常
     */
    public static long copyLarge(@NonNull Reader input, @NonNull Writer output) throws IOException {
        return copyLarge(input, output, new char[DEFAULT_BUFFER_SIZE]);
    }

    /**
     * 复制chars数据，支持超过2GB数据 <p/> 使用提供的缓冲区，因此不需要使用 <code>BufferedReader</code>.
     *
     * @param input  输入
     * @param output 输出
     * @return 实际复制数据长度
     * @throws IOException 出现IO异常
     */
    public static long copyLarge(@NonNull Reader input, @NonNull Writer output, @NonNull char[] buffer) throws IOException {
        long count = 0;
        int n;
        while (EOF != (n = input.read(buffer))) {
            output.write(buffer, 0, n);
            count += n;
        }
        return count;
    }

    /**
     * 读取字符串
     *
     * @param input 输入流
     * @return 字符串
     * @throws IOException 出现异常
     */
    public static String readString(InputStream input) throws IOException {
        byte[] data = read(input);
        return new String(data);
    }

    /**
     * 读取字符串
     *
     * @param input    输入流
     * @param encoding 编码方式
     * @return 字符串
     * @throws IOException 出现异常
     */
    public static String readString(InputStream input, Charset encoding) throws IOException {
        byte[] data = read(input);
        return new String(data, encoding);
    }

    /**
     * 读取字符串
     *
     * @param input    输入流
     * @param encoding 编码方式
     * @return 字符串
     * @throws IOException 出现异常
     */
    public static String readString(InputStream input, String encoding) throws IOException {
        byte[] data = read(input);
        return new String(data, encoding);
    }


    /**
     * 将字符串写入输出流
     *
     * @param data     字符串
     * @param output   输出流
     * @param encoding 编码方式
     * @throws IOException 写入IO异常
     */
    public static void writeString(@Nullable String data, @NonNull OutputStream output, @Nullable Charset encoding) throws IOException {
        if (data != null) {
            output.write(data.getBytes(encoding));
        }
    }

    /**
     * 将字符串写入输出流
     *
     * @param data     字符串
     * @param output   输出流
     * @param encoding 编码方式
     * @throws IOException 写入IO异常
     */
    public static void writeString(@Nullable String data, @NonNull OutputStream output, @Nullable String encoding) throws IOException {
        if (data != null) {
            output.write(data.getBytes(encoding));
        }
    }

    /**
     * 将字符串写入输出流,使用默认编码方式
     *
     * @param data   字符串
     * @param output 输出流
     * @throws IOException 写入IO异常
     */
    public static void writeString(@Nullable String data, OutputStream output)
            throws IOException {
        writeString(data, output, Charset.defaultCharset());
    }

    /**
     * 读取字符串
     *
     * @param input 输入
     * @return 字符串
     * @throws IOException 出现IO异常
     */
    public static String readString(@NonNull Reader input) throws IOException {
        CharArrayWriter out = null;
        try {
            out = new CharArrayWriter();
            copy(input, out);
            return out.toString();
        } finally {
            closeQuietly(out);
        }
    }

    /**
     * 将字符串写入输出流
     *
     * @param data   字符串
     * @param output 输出
     * @throws IOException 写入IO异常
     */
    public static void writeString(@Nullable String data, Writer output) throws IOException {
        if (data != null) {
            output.write(data);
        }
    }

}

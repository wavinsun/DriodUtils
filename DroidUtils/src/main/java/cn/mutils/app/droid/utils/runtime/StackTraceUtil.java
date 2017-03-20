package cn.mutils.app.droid.utils.runtime;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 堆栈实用类
 *
 * Thread.currentThread().getStackTrace() 多端不一致<br>
 * <p>
 * Android environment stack trace:<br>
 * dalvik.system.VMStack.getThreadStackTrace<br>
 * java.lang.Thread.getStackTrace<br>
 * xxx.StackTraceUtil.getCallerElement<br>
 * ... ....<br>
 * <p>
 * <p>
 * Java standard environment stack trace:<br>
 * java.lang.Thread.getStackTrace<br>
 * xxx.StackTraceUtil.getCallerElement<br>
 * ... ...
 */
public class StackTraceUtil {

    private StackTraceUtil() {

    }

    /**
     * 获取当前方法对应堆栈信息
     */
    public static StackTraceElement getCurrentElement() {
        StackTraceElement[] elements = new Throwable().getStackTrace();
        return 1 < elements.length ? elements[1] : elements[elements.length - 1];
    }

    /**
     * 获取调用当前方法的方法对应的堆栈
     */
    public static StackTraceElement getCallerElement() {
        StackTraceElement[] elements = new Throwable().getStackTrace();
        return 2 < elements.length ? elements[2] : elements[elements.length - 1];
    }

    /**
     * 打印堆栈信息
     * @param e 异常
     * @return 堆栈信息
     */
    public static String printStackTrace(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        pw.close();
        return sw.toString();
    }


}

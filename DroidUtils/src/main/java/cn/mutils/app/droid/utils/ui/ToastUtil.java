package cn.mutils.app.droid.utils.ui;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import cn.mutils.app.droid.utils.R;
import cn.mutils.app.droid.utils.os.UiExecutor;

/**
 * Toast实用类
 */
public class ToastUtil {

    private static WeakReference<ToastImpl> sToastRef = null;

    /**
     * 取消显示Toast
     */
    public static synchronized void cancel() {
        ToastImpl toast = sToastRef != null ? sToastRef.get() : null;
        if (toast != null) {
            toast.cancel();
        }
        sToastRef = new WeakReference<ToastImpl>(null);
    }

    /**
     * 显示Toast
     *
     * @param context 上下文
     * @param message 消息
     */
    public static void showToast(Context context, CharSequence message) {
        obtainToast(context).show(message, Toast.LENGTH_SHORT);
    }

    /**
     * 显示Toast
     *
     * @param context    上下文
     * @param message    消息
     * @param dpTextSize 文本字体大小，以dp计算
     */
    public static void showToast(Context context, CharSequence message, float dpTextSize) {
        obtainToast(context).show(message, Toast.LENGTH_SHORT, dpTextSize);
    }

    /**
     * 显示Toast
     *
     * @param context 上下文
     * @param message 消息
     * @param gravity 显示位置
     * @param xOffset 水平偏移量
     * @param yOffset 垂直偏移量
     */
    public static void showToast(Context context, CharSequence message, int gravity, int xOffset, int yOffset) {
        obtainToast(context).show(message, Toast.LENGTH_SHORT, gravity, xOffset, yOffset, 0);
    }

    /**
     * 显示Toast
     *
     * @param context    上下文
     * @param message    消息
     * @param gravity    显示位置
     * @param xOffset    水平偏移量
     * @param yOffset    垂直偏移量
     * @param dpTextSize 文本字体大小，以dp计算
     */
    public static void showToast(Context context, CharSequence message, int gravity,
                                 int xOffset, int yOffset, float dpTextSize) {
        obtainToast(context).show(message, Toast.LENGTH_SHORT, gravity, xOffset, yOffset, dpTextSize);
    }

    /**
     * 显示Toast较长时间
     *
     * @param context 上下文
     * @param message 消息
     */
    public static void showLongToast(Context context, CharSequence message) {
        obtainToast(context).show(message, Toast.LENGTH_LONG);
    }

    /**
     * 显示Toast较长时间
     *
     * @param context    上下文
     * @param message    消息
     * @param dpTextSize 文本字体大小，以dp计算
     */
    public static void showLongToast(Context context, CharSequence message, float dpTextSize) {
        obtainToast(context).show(message, Toast.LENGTH_LONG, dpTextSize);
    }

    /**
     * 显示Toast较长时间
     *
     * @param context 上下文
     * @param message 消息
     * @param gravity 显示位置
     * @param xOffset 水平偏移量
     * @param yOffset 垂直偏移量
     */
    public static void showLongToast(Context context, CharSequence message, int gravity, int xOffset, int yOffset) {
        obtainToast(context).show(message, Toast.LENGTH_LONG, gravity, xOffset, yOffset, 0);
    }

    /**
     * 显示Toast较长时间
     *
     * @param context    上下文
     * @param message    消息
     * @param gravity    显示位置
     * @param xOffset    水平偏移量
     * @param yOffset    垂直偏移量
     * @param dpTextSize 文本字体大小，以dp计算
     */
    public static void showLongToast(Context context, CharSequence message, int gravity,
                                     int xOffset, int yOffset, float dpTextSize) {
        obtainToast(context).show(message, Toast.LENGTH_LONG, gravity, xOffset, yOffset, dpTextSize);
    }

    private static synchronized ToastImpl obtainToast(Context context) {
        ToastImpl toast = sToastRef != null ? sToastRef.get() : null;
        if (toast != null) {
            toast.cancel();
        }
        toast = new ToastImpl(context);
        sToastRef = new WeakReference<ToastImpl>(toast);
        return toast;
    }

    private static class ToastImpl {

        private final Toast mToast;
        private final int mGravityDefault;
        private final int mXOffsetDefault;
        private final int mYOffsetDefault;
        private final float mHorizontalMarginDefault;
        private final float mVerticalMarginDefault;
        private final float mTextSizeDefault;
        private boolean mCancelled;
        private View mContentView;
        private TextView mMessageText;

        ToastImpl(Context context) {
            Context appContext = context.getApplicationContext();
            mToast = new Toast(appContext);
            mGravityDefault = mToast.getGravity();
            mXOffsetDefault = mToast.getXOffset();
            mYOffsetDefault = mToast.getYOffset();
            mHorizontalMarginDefault = mToast.getHorizontalMargin();
            mVerticalMarginDefault = mToast.getVerticalMargin();
            mContentView = LayoutInflater.from(appContext).inflate(R.layout.common_utils_toast, null);
            mToast.setView(mContentView);
            mMessageText = (TextView) mContentView.findViewById(R.id.text_toast);
            mTextSizeDefault = mMessageText.getTextSize();
        }

        @Override
        protected void finalize() throws Throwable {
            cancel();
            super.finalize();
        }

        public void cancel() {
            mCancelled = true;
            mToast.cancel();
        }

        public void show(CharSequence message, int duration) {
            show(message, duration, mGravityDefault, mXOffsetDefault, mYOffsetDefault, 0);
        }

        public void show(CharSequence message, int duration, float dpTextSize) {
            show(message, duration, mGravityDefault, mXOffsetDefault, mYOffsetDefault, dpTextSize);
        }

        public void show(CharSequence message, int duration, int gravity, int xOffset, int yOffset, float dpTextSize) {
            show(message, duration, gravity, xOffset, yOffset, mHorizontalMarginDefault, mVerticalMarginDefault, dpTextSize);
        }

        public void show(final CharSequence message, final int duration, final int gravity, final int xOffset, final int yOffset,
                         final float horizontalMargin, final float verticalMargin, final float dpTextSize) {
            mCancelled = false;
            UiExecutor.post(new Runnable() {
                @Override
                public void run() {
                    if (mCancelled) {
                        return;
                    }
                    mToast.setDuration(duration);
                    mToast.setGravity(gravity, xOffset, yOffset);
                    mToast.setMargin(horizontalMargin, verticalMargin);
                    if (dpTextSize > 0) {
                        mMessageText.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dpTextSize);
                    } else {
                        if (mTextSizeDefault > 0 && mTextSizeDefault != mMessageText.getTextSize()) {
                            mMessageText.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSizeDefault);
                        }
                    }
                    mMessageText.setText(message);
                    mToast.setView(mContentView);
                    mToast.show();
                }
            });
        }

    }

}

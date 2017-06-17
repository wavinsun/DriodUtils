package cn.mutils.app.droid.utils.ui;

import android.view.View;
import android.widget.AdapterView;

/**
 * 避免快速双击实用类
 */
public class NoDBClickUtil {

    public static final int DOUBLE_CLICK_SLOP = 500;

    private NoDBClickUtil() {

    }

    /**
     * 设置点击监听
     *
     * @param view     视图
     * @param listener 监听
     */
    public static void setOnClickListener(View view, View.OnClickListener listener) {
        if (listener == null) {
            view.setOnClickListener(null);
            return;
        }
        view.setOnClickListener(new OnDBClickListener(listener));
    }

    /**
     * 设置子项点击监听
     *
     * @param adapterView 视图
     * @param listener    监听
     */
    public static void setOnItemClickListener(AdapterView<?> adapterView, AdapterView.OnItemClickListener listener) {
        if (listener == null) {
            adapterView.setOnItemClickListener(null);
            return;
        }
        adapterView.setOnItemClickListener(new OnItemDBClickListener(listener));
    }

    static class ClickInterceptor {

        private long mLastClickTime;

        public boolean onInterceptClickEvent() {
            return onInterceptClickEvent(DOUBLE_CLICK_SLOP);
        }

        public boolean onInterceptClickEvent(long slop) {
            long time = System.currentTimeMillis();
            if (time < mLastClickTime) { // 修复修改系统时间造成的问题
                mLastClickTime = time;
                return true;
            } else if (time - mLastClickTime < slop) {
                return true;
            }
            mLastClickTime = time;
            return false;
        }
    }

    static class OnDBClickListener implements View.OnClickListener {

        private View.OnClickListener mTarget;

        private ClickInterceptor mInterceptor = new ClickInterceptor();

        public OnDBClickListener(View.OnClickListener target) {
            mTarget = target;
        }

        @Override
        public void onClick(final View v) {
            if (!mInterceptor.onInterceptClickEvent()) {
                v.setEnabled(false);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v.setEnabled(true);
                    }
                }, DOUBLE_CLICK_SLOP);
                mTarget.onClick(v);
            }
        }

    }

    static class OnItemDBClickListener implements AdapterView.OnItemClickListener {

        private AdapterView.OnItemClickListener mTarget;

        private ClickInterceptor mInterceptor = new ClickInterceptor();

        public OnItemDBClickListener(AdapterView.OnItemClickListener target) {
            mTarget = target;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (!mInterceptor.onInterceptClickEvent()) {
                mTarget.onItemClick(parent, view, position, id);
            }
        }

    }

}

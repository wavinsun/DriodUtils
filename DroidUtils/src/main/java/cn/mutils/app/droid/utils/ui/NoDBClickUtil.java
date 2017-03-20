package cn.mutils.app.droid.utils.ui;

import android.view.View;
import android.widget.AdapterView;

/**
 * 避免快速双击实用类
 */
public class NoDBClickUtil {

    public static final int DOUBLE_CLICK_SLOP = 500;

    private static long sLastClickTime;

    private NoDBClickUtil() {

    }

    private static boolean onInterceptClickEvent() {
        return onInterceptClickEvent(DOUBLE_CLICK_SLOP);
    }

    private static boolean onInterceptClickEvent(long slop) {
        long time = System.currentTimeMillis();
        if (time - sLastClickTime < slop) {
            return true;
        }
        sLastClickTime = time;
        return false;
    }

    /**
     * 设置点击监听
     * @param view 视图
     * @param listener 监听
     */
    public static void setOnClickListener(View view, View.OnClickListener listener) {
        view.setOnClickListener(new OnDBClickListener(listener));
    }

    /**
     * 设置子项点击监听
     * @param adapterView 视图
     * @param listener 监听
     */
    public static void setOnItemClickListener(AdapterView<?> adapterView, AdapterView.OnItemClickListener listener) {
        adapterView.setOnItemClickListener(new OnItemDBClickListener(listener));
    }

    static class OnDBClickListener implements View.OnClickListener {

        private View.OnClickListener mTarget;

        public OnDBClickListener(View.OnClickListener target) {
            mTarget = target;
        }

        @Override
        public void onClick(final View v) {
            if (!onInterceptClickEvent()) {
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

        public OnItemDBClickListener(AdapterView.OnItemClickListener target) {
            mTarget = target;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (!onInterceptClickEvent()) {
                mTarget.onItemClick(parent, view, position, id);
            }
        }

    }

}

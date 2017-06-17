package cn.mutils.app.droid.utils.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContextWrapper;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import cn.mutils.app.droid.utils.os.UiExecutor;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * 对话框封装类
 */
public class CompatDialog extends Dialog {

    private volatile boolean mIsShowing;
    private volatile boolean mAllowInvokeShow = true;
    private final Activity mActivity;
    private final DialogRootView mRootView;

    /**
     * 构造函数
     *
     * @param activity 上下文
     */
    public CompatDialog(Activity activity) {
        super(new ContextWrapper(activity));
        mActivity = activity;
        mRootView = new DialogRootView(this);
    }

    /**
     * 构造函数
     *
     * @param activity 上下文
     * @param theme    主题
     */
    public CompatDialog(Activity activity, int theme) {
        super(new ContextWrapper(activity), theme);
        mActivity = activity;
        mRootView = new DialogRootView(this);
    }

    /**
     * 构造函数
     *
     * @param activity       上下文
     * @param cancelable     是否可以取消
     * @param cancelListener 取消监听
     */
    public CompatDialog(Activity activity, boolean cancelable, OnCancelListener cancelListener) {
        super(new ContextWrapper(activity), cancelable, cancelListener);
        mActivity = activity;
        mRootView = new DialogRootView(this);
    }

    /**
     * 设置内容视图
     *
     * @param layoutResID 视图资源编号
     */
    @Override
    public void setContentView(int layoutResID) {
        mRootView.removeAllViews();
        this.getLayoutInflater().inflate(layoutResID, mRootView, true);
        super.setContentView(mRootView);
    }

    /**
     * 设置内容视图
     *
     * @param view 视图
     */
    @Override
    public void setContentView(View view) {
        mRootView.removeAllViews();
        mRootView.addView(view, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        super.setContentView(mRootView);
    }

    /**
     * 设置内容视图
     *
     * @param view   视图
     * @param params 布局参数
     */
    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mRootView.removeAllViews();
        mRootView.addView(view, params);
        super.setContentView(mRootView);
    }

    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

    /**
     * 是否显示
     *
     * @return
     */
    @Override
    public boolean isShowing() {
        return mIsShowing;
    }

    /**
     * 显示对话框
     */
    @Override
    public void show() {
        mIsShowing = true;
        UiExecutor.post(new Runnable() {
            @Override
            public void run() {
                if (mAllowInvokeShow) {
                    if (mActivity.isFinishing()) {
                        return;
                    }
                    try {
                        CompatDialog.super.show();
                    } catch (Throwable e) {
                        // exception happens
                    }
                } else {
                    mAllowInvokeShow = true;
                }
            }
        });
    }

    /**
     * 关闭对话框
     */
    @Override
    public void dismiss() {
        mIsShowing = false;
        UiExecutor.post(new Runnable() {
            @Override
            public void run() {
                if (CompatDialog.super.isShowing()) {
                    try {
                        CompatDialog.super.dismiss();
                    } catch (Throwable e) {
                        // exception happens
                    }
                } else {
                    mAllowInvokeShow = false;
                }
            }
        });
    }

    private static class DialogRootView extends LinearLayout {

        private CompatDialog mDialog;

        public DialogRootView(CompatDialog dialog) {
            super(dialog.getContext());
            mDialog = dialog;
            this.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        }

        @Override
        protected void onLayout(boolean changed, int l, int t, int r, int b) {
            super.onLayout(changed, l, t, r, b);
            mDialog.onLayout(changed, l, t, r, b);
        }
    }
}

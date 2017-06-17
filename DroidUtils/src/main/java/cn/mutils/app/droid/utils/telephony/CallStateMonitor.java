package cn.mutils.app.droid.utils.telephony;

import android.content.Context;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 通话状态监听器
 * <p>
 * Created by wenhua.ywh on 2017/6/16.
 */
public class CallStateMonitor {

    /**
     * 无任何状态时
     */
    public static final int CALL_STATE_IDLE = TelephonyManager.CALL_STATE_IDLE;
    /**
     * 电话进来时，响铃状态
     */
    public static final int CALL_STATE_RINGING = TelephonyManager.CALL_STATE_RINGING;
    /**
     * 接起电话时，通话状态
     */
    public static final int CALL_STATE_OFFHOOK = TelephonyManager.CALL_STATE_OFFHOOK;

    /**
     * 通话状态变化观察者
     */
    public static abstract class CallStateObserver {
        /**
         * 通话状态变化
         *
         * @param state          通话状态
         * @param incomingNumber 电话号码
         */
        public abstract void onCallStateChanged(int state, String incomingNumber);
    }

    private static volatile CallStateMonitor sInstance;

    private int mCallState = CALL_STATE_IDLE;
    private List<CallStateObserver> mObservers = new CopyOnWriteArrayList<CallStateObserver>();
    private Context mContext;
    private PhoneStateChangeListener mListener;

    private CallStateMonitor() {

    }

    /**
     * 获取当前通话状态
     *
     * @return
     */
    public int getCallState() {
        return mCallState;
    }

    /**
     * 添加通话状态变化观察者
     *
     * @param observer 观察者
     */
    public void addObserver(CallStateObserver observer) {
        if (observer == null) {
            return;
        }
        mObservers.add(observer);
    }

    /**
     * 移除通话状态变化观察者
     *
     * @param observer 观察者
     */
    public void removeObserver(CallStateObserver observer) {
        if (observer == null) {
            return;
        }
        mObservers.remove(observer);
    }

    /**
     * 清空通话状态变化观察者
     */
    public void clearObservers() {
        mObservers.clear();
    }

    /**
     * 安装监听器
     *
     * @param context 上下文
     */
    public synchronized void install(Context context) {
        if (mContext != null) {
            return;
        }
        if (mContext == context) {
            return;
        }
        mContext = context;
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            mCallState = CALL_STATE_IDLE;
            return;
        }
        mCallState = tm.getCallState();
        if (mListener == null) {
            mListener = new PhoneStateChangeListener();
        }
        tm.listen(mListener, PhoneStateListener.LISTEN_CALL_STATE);
    }

    /**
     * 移除监听器
     *
     * @param context 上下文
     */
    public synchronized void uninstall(Context context) {
        if (mContext == null) {
            return;
        }
        if (mContext != context) {
            return;
        }
        TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (tm == null) {
            mContext = null;
            return;
        }
        if (mListener != null) {
            tm.listen(mListener, PhoneStateChangeListener.LISTEN_NONE);
        }
        mContext = null;
    }

    private synchronized void dispatchChangeEvent(int state, String incomingNumber) {
        mCallState = state;
        for (CallStateObserver observer : mObservers) {
            observer.onCallStateChanged(state, incomingNumber);
        }
    }

    /**
     * 单例方式获取实例
     *
     * @return 监听器
     */
    public static CallStateMonitor getInstance() {
        if (sInstance == null) {
            synchronized (CallStateMonitor.class) {
                if (sInstance == null) {
                    sInstance = new CallStateMonitor();
                }
            }
        }
        return sInstance;
    }

    class PhoneStateChangeListener extends PhoneStateListener {
        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            super.onCallStateChanged(state, incomingNumber);
            dispatchChangeEvent(state, incomingNumber);
        }
    }
}

package cn.mutils.app.droid.utils.device;

import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;
import android.os.Build;
import android.os.Looper;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import cn.mutils.app.droid.utils.os.UiExecutor;

/**
 * 网络状态监听器
 *
 * Created by wenhua.ywh on 2017/3/9.
 */
public class ConnectivityMonitor {

    /**
     * 未知网络
     */
    public static final int NETWORK_TYPE_UNKNOWN = NetworkUtil.NETWORK_TYPE_UNKNOWN;
    /**
     * 2G网络
     */
    public static final int NETWORK_TYPE_2G = NetworkUtil.NETWORK_TYPE_2G;
    /**
     * 3G网络
     */
    public static final int NETWORK_TYPE_3G = NetworkUtil.NETWORK_TYPE_3G;
    /**
     * 4G网络
     */
    public static final int NETWORK_TYPE_4G = NetworkUtil.NETWORK_TYPE_4G;
    /**
     * Wifi网络
     */
    public static final int NETWORK_TYPE_WIFI = NetworkUtil.NETWORK_TYPE_WIFI;

    private static final int ANDROID_N = 24;

    /**
     * 网络变化观察者
     */
    public static abstract class ConnectivityObserver {
        /**
         * 网络发送变化
         *
         * 由监听器内部保证在主线程回调
         *
         * @param newNetworkType 新网络类型
         * @param oldNetworkType 旧网络类型
         */
        public abstract void onConnectivityChanged(int newNetworkType, int oldNetworkType);
    }

    private static volatile ConnectivityMonitor sInstance;

    private int mNetworkType = NETWORK_TYPE_UNKNOWN;
    private List<ConnectivityObserver> mObservers = new CopyOnWriteArrayList<ConnectivityObserver>();
    private ConnectivityChangeReceiver mReceiver;
    private NetworkChangeCallback mCallback;
    private Context mContext;

    private ConnectivityMonitor() {

    }

    /**
     * 获取当前网络类型
     *
     * @return 网络类型
     */
    public int getNetworkType() {
        return mNetworkType;
    }

    /**
     * 添加网络变化观察者
     *
     * @param observer 观察者
     */
    public void addObserver(ConnectivityObserver observer) {
        if (observer == null) {
            return;
        }
        mObservers.add(observer);
    }

    /**
     * 移除网络变化观察者
     *
     * @param observer 观察者
     */
    public void removeObserver(ConnectivityObserver observer) {
        if (observer == null) {
            return;
        }
        mObservers.remove(observer);
    }

    /**
     * 清空网络变化观察者
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
        mContext = context;
        mNetworkType = NetworkUtil.getNetworkType(context);
        if (Build.VERSION.SDK_INT < ANDROID_N) {
            if (mReceiver == null) {
                mReceiver = new ConnectivityChangeReceiver();
            }
            IntentFilter filter = new IntentFilter();
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            mContext.registerReceiver(mReceiver, filter);
        } else {
            if (mCallback == null) {
                mCallback = new NetworkChangeCallback();
            }
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null) {
                NetworkRequest.Builder builder = new NetworkRequest.Builder();
                cm.registerNetworkCallback(builder.build(), mCallback);
            }
        }
    }

    /**
     * 移除监听器
     */
    public synchronized void uninstall(Context context) {
        if (mContext == null) {
            return;
        }
        if (mContext != context) {
            return;
        }
        if (Build.VERSION.SDK_INT < ANDROID_N) {
            if (mReceiver != null) {
                mContext.unregisterReceiver(mReceiver);
            }
        } else {
            ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm != null && mCallback != null) {
                cm.unregisterNetworkCallback(mCallback);
            }
        }
        mContext = null;
    }

    private synchronized void dispatchChangeEvent(int newNetworkType) {
        int oldNetworkType = mNetworkType;
        mNetworkType = newNetworkType;
        for (ConnectivityObserver observer : mObservers) {
            observer.onConnectivityChanged(newNetworkType, oldNetworkType);
        }
    }

    /**
     * 单例方式获取实例
     *
     * @return 监听器
     */
    public static ConnectivityMonitor getInstance() {
        if (sInstance == null) {
            synchronized (ConnectivityMonitor.class) {
                if (sInstance == null) {
                    sInstance = new ConnectivityMonitor();
                }
            }
        }
        return sInstance;
    }

    class ConnectivityChangeReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
                dispatchChangeEvent(NetworkUtil.getNetworkType(context));
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    class NetworkChangeCallback extends ConnectivityManager.NetworkCallback {

        @Override
        public void onAvailable(Network network) {
            doDispatchChangeEvent();
        }

        @Override
        public void onLost(Network network) {
            doDispatchChangeEvent();
        }

        private void doDispatchChangeEvent() {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                dispatchChangeEvent(NetworkUtil.getNetworkType(mContext));
            } else {
                UiExecutor.post(new Runnable() {
                    @Override
                    public void run() {
                        dispatchChangeEvent(NetworkUtil.getNetworkType(mContext));
                    }
                });
            }
        }
    }

}

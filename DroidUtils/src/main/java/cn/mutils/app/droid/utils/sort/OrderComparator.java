package cn.mutils.app.droid.utils.sort;

import java.util.Comparator;

abstract class OrderComparator<T> implements Comparator<T> {

    protected boolean mASC = true;

    public boolean isASC() {
        return mASC;
    }

    public boolean setASC(boolean asc) {
        return mASC = asc;
    }

}

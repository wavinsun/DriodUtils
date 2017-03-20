package cn.mutils.app.droid.utils.sort;

import java.io.File;

class FileLengthComparator extends OrderComparator<File> {

    @Override
    public int compare(File lhs, File rhs) {
        long diff = lhs.length() - rhs.length();
        if (diff > 0) {
            return mASC ? 1 : -1;
        } else if (diff < 0) {
            return mASC ? -1 : 1;
        } else {
            return 0;
        }
    }

}

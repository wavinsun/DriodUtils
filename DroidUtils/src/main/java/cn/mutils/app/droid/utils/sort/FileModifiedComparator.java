package cn.mutils.app.droid.utils.sort;

import java.io.File;

class FileModifiedComparator extends OrderComparator<File> {

    @Override
    public int compare(File lhs, File rhs) {
        long diff = lhs.lastModified() - rhs.lastModified();
        if (diff > 0) {
            return mASC ? 1 : -1;
        } else if (diff < 0) {
            return mASC ? -1 : 1;
        } else {
            return 0;
        }
    }

}

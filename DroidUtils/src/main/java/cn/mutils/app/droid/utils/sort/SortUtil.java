package cn.mutils.app.droid.utils.sort;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * 排序实用类
 */
public class SortUtil {

    private SortUtil() {

    }

    /**
     * 根据文件修改时间进行升序排序
     *
     * @param list 文件列表
     * @return 排序完成的列表
     */
    public static List<File> sortByModified(List<File> list) {
        return sortByModified(list, true);
    }

    /**
     * 根据文件修改时间进行排序
     *
     * @param list 文件列表
     * @param asc  是否是升序排序
     * @return 排序完成的列表
     */
    public static List<File> sortByModified(List<File> list, boolean asc) {
        FileModifiedComparator comparator = new FileModifiedComparator();
        comparator.setASC(asc);
        Collections.sort(list, comparator);
        return list;
    }

    /**
     * 根据文件大小进行升序排序
     *
     * @param list 文件列表
     * @return 排序完成的列表
     */
    public static List<File> sortByLength(List<File> list) {
        return sortByLength(list, true);
    }

    /**
     * 根据文件大小进行升序排序
     *
     * @param list 文件列表
     * @param asc  是否是升序排序
     * @return 排序完成的列表
     */
    public static List<File> sortByLength(List<File> list, boolean asc) {
        FileLengthComparator comparator = new FileLengthComparator();
        comparator.setASC(asc);
        Collections.sort(list, comparator);
        return list;
    }


}

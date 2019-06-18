package com.hust_twj.zademo.utils;

import java.util.Collection;
import java.util.List;

/**
 * Description ：
 * Created by Wenjing.Tang on 2019-06-14.
 */
public class CollectionUtils {

    private CollectionUtils() {
    }

    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    public static boolean isNotEmpty(Collection collection) {
        return !isEmpty(collection);
    }

    public static int size(Collection collection) {
        return collection == null ? 0 : collection.size();
    }

    public static boolean cantions(Object[] array, Object object) {
        if (array == null) {
            return false;
        }
        for (Object o : array) {
            if (o == object) {
                return true;
            }
            if (object != null && object.equals(o)) {
                return true;
            }
            if (o != null && o.equals(object)) {
                return true;
            }
        }
        return false;
    }

    public static int getSize(Collection collection) {
        return collection == null ? 0 : collection.size();
    }

    public static boolean isPositionValid(Collection collection, int position) {
        return !isEmpty(collection) && (position >= 0) && (position < collection.size());
    }

    public static boolean isContainsSameData(List list1, List list2) {
        if (list1 == null && list1 == list2) {
            return true;
        }
        if (list1 == null || list2 == null) {
            return false;
        }
        if (list1.size() == list2.size()) {
            for (int i = 0, size1 = list1.size(); i < size1; i++) {
                if (list1.get(i) != list2.get(i)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}

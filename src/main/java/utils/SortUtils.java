package utils;

import aquality.selenium.core.logging.Logger;
import com.google.common.collect.Ordering;

import java.util.List;

public class SortUtils {
    public static boolean isSortedPostsById(List<? extends Comparable> sortedList) {
        Logger.getInstance().debug("Checking posts list for sorting by Id");
        return Ordering.natural().isOrdered(sortedList);
    }
}

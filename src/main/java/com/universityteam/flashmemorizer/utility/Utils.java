package com.universityteam.flashmemorizer.utility;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static <T> List<T> getElementsInAWithoutB(List<T> a, List<T> b) {
        List<T> result = new ArrayList<>(a);
        result.removeAll(b);
        return result;
    }
}

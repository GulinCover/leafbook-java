package org.leafbook.utils.tools;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Covert2Tools {
    public static boolean isDigital(String str) {
        if (Objects.isNull(str)) return false;
        Pattern pattern = Pattern.compile("^[0-9]*$");
        return pattern.matcher(str).matches();
    }

    public static Long covertToLong(String str) {
        if (Objects.isNull(str)) return -1L;
        Pattern pattern = Pattern.compile("^[0-9]*$");
        if (pattern.matcher(str).matches()) return Long.parseLong(str);
        return -1L;
    }

    public static Integer covertToInteger(String str) {
        if (Objects.isNull(str)) return -1;
        Pattern pattern = Pattern.compile("^[0-9]*$");
        if (pattern.matcher(str).matches()) return Integer.parseInt(str);
        return -1;
    }

    public static List<Long> covertToIntegers(String[] str) {
        if (Objects.isNull(str)) return null;
        List<Long> longs = new LinkedList<>();
        for (String s : str) {
            Long aLong = covertToLong(s);
            if (aLong == null) continue;
            longs.add(aLong);
        }
        return longs;
    }
}

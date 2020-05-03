package com.sg.eirp.program.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonUtil {

    public static final String DEFAULT_DELIMITER = ",";

    public static List<String> convertArrayToList(String[] array) {
        List<String> list = new ArrayList<>();
        if (array != null) {
            list.addAll(Arrays.asList(array));
        }
        return list;
    }

    public static String[] convertStringToArray(String str, String delimiter) {
        String[] resultArray = null;
        if (str != null && delimiter != null) {
            resultArray = str.split(delimiter);
        }
        return resultArray;
    }

    public static List<String> convertStringToList(String str, String delimiter) {
        return convertArrayToList(convertStringToArray(str, delimiter));
    }

    public static List<String> convertStringToList(String str) {
        return convertStringToList(str, DEFAULT_DELIMITER);
    }
}
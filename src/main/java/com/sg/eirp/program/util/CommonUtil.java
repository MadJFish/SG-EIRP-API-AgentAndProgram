package com.sg.eirp.program.util;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommonUtil implements CommonConstants {

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

    public static String convertListToString(List<String> inputList, String delimiter) {
        StringBuilder sb = new StringBuilder();
        if (inputList != null && inputList.size() > 0) {
            for(String str : inputList) {
                if (!sb.toString().isEmpty()) {
                    sb.append(delimiter);
                }
                sb.append(str);
            }
        }
        return sb.toString();
    }

    public static String convertListToString(List<String> inputList) {
        return convertListToString(inputList, DEFAULT_DELIMITER);
    }

    public static String convertObjectToJSON(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            // Java object to JSON file
            mapper.writeValue(new File("c:\\logs\\staff.json"), object);

            // Java object to JSON string
            return mapper.writeValueAsString(object);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static String convertBooleanToBit(Boolean value) {
        return value ? "1" : "0";
    }

    public static Boolean convertBitToBoolean(String bit) {
        return bit != null && bit.equals("1");
    }
}
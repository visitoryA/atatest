package com.backend.atatest.util;

import java.lang.reflect.Field;
import java.util.List;
import java.util.regex.Pattern;

public class ValidatorUtil {

    public static boolean validateWithRegex(String pattern, String value) {
        Pattern regexPattern = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE);
        return regexPattern.matcher(value).matches();
    }

    public static boolean invalidFieldName(List<String> values, Field[] fields) {
        boolean isMatch = false;
        for (String val : values) {
            isMatch = false;
            for (Field field : fields) {
                isMatch = field.getName().equalsIgnoreCase(val);
                if (isMatch) {
                    break;
                }
            }
            if (!isMatch) {
                break;
            }
        }
        return !isMatch;
    }
}

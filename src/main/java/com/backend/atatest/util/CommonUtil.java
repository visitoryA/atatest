package com.backend.atatest.util;

import com.backend.atatest.constant.CommonConstant;
import org.apache.logging.log4j.util.Strings;

import static com.backend.atatest.constant.CommonConstant.COMMA;

public class CommonUtil {
    public static Integer convertStrToInt(String value) {
        if (Strings.isBlank(value)) {
            return null;
        }
        String[] strDecimal = value.split("\\.");
        String replacedVal = strDecimal[0].replace(COMMA, CommonConstant.EMPTY);
        return Integer.parseInt(replacedVal);
    }

    public static void appendStrBuilder(StringBuilder strBuilder, String value) {
        if (!strBuilder.isEmpty()) {
            strBuilder.append(COMMA + " ").append(value);
        } else {
            strBuilder.append(value);
        }
    }
}

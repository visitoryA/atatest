package com.backend.atatest.constant;

import static com.backend.atatest.constant.CommonConstant.SORT_TYPE.ASC;
import static com.backend.atatest.constant.CommonConstant.SORT_TYPE.DESC;

public final class CommonConstant {
    public static final String COMMA = ",";
    public static final String EMPTY = "";
    public static final String SPACE = " ";
    public static final String PIPE = "|";

    public static final String JSON_CONTENT_TYPE = "application/json";

    public static final class SORT_TYPE {
        public static final String ASC = "ASC";
        public static final String DESC = "DESC";
    }

    public static final class REGEX {
        public static final String SORT_TYPE = "^(" + ASC + "|" + DESC +")$";
        public static final String NUM_FORMAT = "^((\\d*)(\\d{1,3}(?:,\\d{3})*))(?:\\.\\d+)?$";
    }
}

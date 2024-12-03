package com.backend.atatest.constant;

import com.backend.atatest.model.JobListRequest;
import com.backend.atatest.util.CommonUtil;
import org.apache.logging.log4j.util.Strings;

import static com.backend.atatest.constant.DeclaredFields.JobListResponseModel.GENDER;

public enum GenderEnum {
    F("Female"),
    M("Male"),
    N("None");

    private final String gender;

    GenderEnum(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public static void validate(JobListRequest request, StringBuilder strBuilder) {
        if (Strings.isNotBlank(request.getGender())) {
            for (GenderEnum enumVal : values()) {
                if (enumVal.name().equalsIgnoreCase(request.getGender())) {
                    return;
                }
            }
            CommonUtil.appendStrBuilder(strBuilder, GENDER);
        }
    }
}

package com.backend.atatest.constant;

import com.backend.atatest.model.JobListRequest;
import com.backend.atatest.util.CommonUtil;
import org.apache.logging.log4j.util.Strings;

import static com.backend.atatest.constant.DeclaredFields.JobListResponseModel.JOB_TYPE;

public enum JobTypeEnum {
    ALL("All-type"),
    PERM("Permanent"),
    PARTTIME("Part-time"),
    TEMP("Contract/Temp");

    private final String jobType;
    JobTypeEnum(String jobType) { this.jobType = jobType; }

    public String getJobType() {
        return this.jobType;
    }

    public static void validate(JobListRequest request, StringBuilder strBuilder) {
        if (Strings.isNotBlank(request.getJobType())) {
            for (JobTypeEnum enumVal : values()) {
                if (enumVal.name().equalsIgnoreCase(request.getJobType())) {
                    return ;
                }
            }
            CommonUtil.appendStrBuilder(strBuilder, JOB_TYPE);
        }
    }
}

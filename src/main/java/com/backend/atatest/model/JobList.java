package com.backend.atatest.model;

import com.backend.atatest.constant.GenderEnum;
import com.backend.atatest.constant.JobTypeEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.backend.atatest.constant.DeclaredFields.JobListResponseModel.JOB_TITLE;

@Getter
@Setter
@AllArgsConstructor
public class JobList {
    private String jobId;
    private String jobTitle;
    private JobTypeEnum jobType;
    private GenderEnum gender;
    private String jobDesc;
    private String salary;
    private String experience;
    private List<String> jobTag;
}

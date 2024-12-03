package com.backend.atatest.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

import static com.backend.atatest.constant.DeclaredFields.JobListResponseModel.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({JOB_ID, JOB_TITLE, JOB_TYPE, GENDER, EXPERIENCE, SALARY, "job_description", JOB_TAG})
public class JobListData {
    @JsonProperty(JOB_ID)
    private String jobId;
    @JsonProperty(JOB_TITLE)
    private String jobTitle;
    @JsonProperty(JOB_TYPE)
    private String jobType;
    @JsonProperty(GENDER)
    private String gender;
    @JsonProperty(SALARY)
    private String salary;
    @JsonProperty("job_description")
    private String jobDesc;
    @JsonProperty(EXPERIENCE)
    private String experience;
    @JsonProperty(JOB_TAG)
    private List<String> jobTag;
}
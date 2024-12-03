package com.backend.atatest.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Field;
import java.util.List;

@Builder
@Getter
@Setter
public class JobListRequest {
    private String jobId;
    private String jobTitle;
    private String jobType;
    private String jobTag;
    private String gender;
    private String salary;
    private Integer salaryInt;
    private String experience;
    private Integer experienceInt;
    private List<String> fieldStr;
    private String sortField;
    private String sortType;
}
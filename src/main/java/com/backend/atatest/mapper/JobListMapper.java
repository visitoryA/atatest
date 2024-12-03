package com.backend.atatest.mapper;

import com.backend.atatest.constant.DeclaredFields.JobListResponseModel;
import com.backend.atatest.model.JobList;
import com.backend.atatest.model.JobListData;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Component
public class JobListMapper {
    public List<JobListData> map(Stream<JobList> data, List<String> fields) {
        List<JobListData> jobLists = new ArrayList<>();
        boolean hasFilteredField = !ObjectUtils.isEmpty(fields);

        data.forEach(it ->
            jobLists.add(hasFilteredField ? mapDataWithFilteredField(it, fields) : mapDataWithAllField(it))
        );
        return jobLists;
    }

    private JobListData mapDataWithFilteredField(JobList job, List<String> fields) {
        JobListData dataResp = JobListData.builder()
                .jobTitle(job.getJobTitle())
                .build();
        for (String fieldName : fields) {
            setObjectValue(dataResp, job, fieldName);
        }
        return dataResp;
    }

    private JobListData mapDataWithAllField(JobList job) {
        return JobListData.builder()
                .jobId(job.getJobId())
                .jobTitle(job.getJobTitle())
                .jobType(job.getJobType().getJobType())
                .gender(job.getGender().getGender())
                .experience(job.getExperience())
                .salary(job.getSalary())
                .jobDesc(job.getJobDesc())
                .jobTag(job.getJobTag())
                .build();
    }

    private void setObjectValue(JobListData data, JobList rawData, String fieldName) {
        switch (fieldName) {
            case JobListResponseModel.JOB_ID:
                data.setJobId(rawData.getJobId());
                break;
            case JobListResponseModel.JOB_TYPE:
                data.setJobType(rawData.getJobType().getJobType());
                break;
            case JobListResponseModel.GENDER:
                data.setGender(rawData.getGender().getGender());
                break;
            case JobListResponseModel.EXPERIENCE:
                data.setExperience(rawData.getExperience());
                break;
            case JobListResponseModel.SALARY:
                data.setSalary(rawData.getSalary());
                break;
            case JobListResponseModel.JOB_TAG:
                data.setJobTag(rawData.getJobTag());
                break;
        }
    }
}

package com.backend.atatest.mapper;

import com.backend.atatest.constant.DeclaredFields.JobListResponseModel;
import com.backend.atatest.constant.GenderEnum;
import com.backend.atatest.constant.JobTypeEnum;
import com.backend.atatest.model.JobList;
import com.backend.atatest.model.JobListData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class JobListMapperTest {
    @InjectMocks
    private JobListMapper mapper;

    @Test
    void mapDataWithEmptyFieldName() {
        List<JobList> jobLists = mockJobList();
        List<JobListData> actualResult = mapper.map(jobLists.stream(), Collections.emptyList());

        assertEquals(jobLists.size(), actualResult.size());
        for (int i = 0; i < jobLists.size(); i++) {
            JobList job = jobLists.get(i);
            JobListData jobData = actualResult.get(i);
            assertEquals(job.getJobId(), jobData.getJobId());
            assertEquals(job.getJobTitle(), jobData.getJobTitle());
            assertEquals(job.getJobType().getJobType(), jobData.getJobType());
            assertEquals(job.getGender().getGender(), jobData.getGender());
            assertEquals(job.getSalary(), jobData.getSalary());
            assertEquals(job.getExperience(), jobData.getExperience());
            assertEquals(job.getJobTag().size(), jobData.getJobTag().size());
        }
    }

    @Test
    void mapDataWithAllFieldName() {
        List<String> fieldNames = List.of(JobListResponseModel.JOB_ID, JobListResponseModel.JOB_TYPE,
                JobListResponseModel.JOB_TAG, JobListResponseModel.EXPERIENCE, JobListResponseModel.GENDER, JobListResponseModel.SALARY);
        List<JobList> jobLists = mockJobList();
        List<JobListData> actualResult = mapper.map(jobLists.stream(), fieldNames);

        assertEquals(jobLists.size(), actualResult.size());
        for (int i = 0; i < jobLists.size(); i++) {
            JobList job = jobLists.get(i);
            JobListData jobData = actualResult.get(i);
            assertEquals(job.getJobId(), jobData.getJobId());
            assertEquals(job.getJobTitle(), jobData.getJobTitle());
            assertEquals(job.getJobType().getJobType(), jobData.getJobType());
            assertEquals(job.getGender().getGender(), jobData.getGender());
            assertEquals(job.getSalary(), jobData.getSalary());
            assertEquals(job.getExperience(), jobData.getExperience());
            assertEquals(job.getJobTag().size(), jobData.getJobTag().size());
        }
    }

    @Test
    void mapDataWithFieldNames() {
        List<String> fieldNames = List.of(JobListResponseModel.JOB_ID);
        List<JobList> jobLists = mockJobList();
        List<JobListData> actualResult = mapper.map(jobLists.stream(), fieldNames);

        assertEquals(jobLists.size(), actualResult.size());
        for (int i = 0; i < jobLists.size(); i++) {
            JobList job = jobLists.get(i);
            JobListData jobData = actualResult.get(i);
            assertEquals(job.getJobId(), jobData.getJobId());
            assertEquals(job.getJobTitle(), jobData.getJobTitle());
            assertNull(jobData.getJobType());
            assertNull(jobData.getGender());
            assertNull(jobData.getSalary());
            assertNull(jobData.getExperience());
            assertNull(jobData.getJobTag());
        }
    }

    private List<JobList> mockJobList() {
        JobList job1 = new JobList("1", "Job Title 1", JobTypeEnum.TEMP, GenderEnum.N, "job description", "15000 - 35000", "0 - 3", Collections.emptyList());
        JobList job2 = new JobList("2", "Job Title 2", JobTypeEnum.PERM, GenderEnum.F, "job description 2", "25000 - 60000", "1 - 4", List.of("job", "tag"));
        return List.of(job1, job2);
    }
}

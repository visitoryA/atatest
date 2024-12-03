package com.backend.atatest.controller;

import com.backend.atatest.model.JobListResponse;
import com.backend.atatest.service.JobListService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.backend.atatest.constant.CommonConstant.JSON_CONTENT_TYPE;
import static com.backend.atatest.constant.DeclaredFields.*;
import static com.backend.atatest.constant.DeclaredFields.JobListResponseModel.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class JobListControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private JobListService service;

    private static final String URI = "/v1/job-list";

    @Test
    void getJobListSuccess() throws Exception {
        doReturn(JobListResponse.builder().build()).when(service).getJobList(any());
        mockMvc.perform(MockMvcRequestBuilders.get(URI))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void getJobListSuccessWithParam() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .param(JOB_TYPE, "TEMP")
                        .param(GENDER, "N")
                        .param(SALARY, "100,000")
                        .param(EXPERIENCE, "3.0")
                        .param(FIELDS, "job_title")
                        .param(SORT, "job_id"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$").exists());
    }

    @Test
    void getJobListWithMissingField() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .param(SORT_TYPE, "desc"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.description").value("sort is missing"));
    }

    @Test
    void getJobListWithInvalidFields() throws Exception {
        String expDesc = "job_type, gender, salary, experience, fields, sort, sort_type is invalid";
        mockMvc.perform(MockMvcRequestBuilders.get(URI)
                        .param(JOB_TYPE, "jobType")
                        .param(GENDER, "gender")
                        .param(SALARY, "10.0.0")
                        .param(EXPERIENCE, "3,0")
                        .param(FIELDS, "mock,field")
                        .param(SORT, "sortField")
                        .param(SORT_TYPE, "sortType"))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(JSON_CONTENT_TYPE))
                .andExpect(jsonPath("$.description").value(expDesc));
    }
}

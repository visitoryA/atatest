package com.backend.atatest.service;

import com.backend.atatest.constant.DeclaredFields.JobListResponseModel;
import com.backend.atatest.constant.ResponseMessageEnum;
import com.backend.atatest.dataset.JobListDataSet;
import com.backend.atatest.mapper.JobListMapper;
import com.backend.atatest.model.JobList;
import com.backend.atatest.model.JobListData;
import com.backend.atatest.model.JobListRequest;
import com.backend.atatest.model.JobListResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobListServiceTest {
    @InjectMocks
    private JobListService service;
    @Mock
    private JobListMapper mapper;
    @Captor
    private ArgumentCaptor<Stream<JobList>> jobListArgumentCaptor;

    @Test
    void getJobListWithoutFilterSortByJobTitle() {
        JobListRequest request = JobListRequest.builder()
                .sortField(JobListResponseModel.JOB_TITLE).build();
        JobListData data = JobListData.builder().build();
        doReturn(List.of(data)).when(mapper).map(any(), any());

        JobListResponse response = service.getJobList(request);

        verify(mapper, times(1)).map(jobListArgumentCaptor.capture(), any());
        assertEquals(200, response.getStatusCode());
        assertEquals(ResponseMessageEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(1, response.getTotalData());
        assertEquals(1, response.getData().size());
        assertEquals(data, response.getData().get(0));

        List<JobList> dataSetVal = jobListArgumentCaptor.getValue().toList();
        assertEquals(4, dataSetVal.size());
        assertEquals(JobListDataSet.dataSet.get(2), dataSetVal.get(0));
        assertEquals(JobListDataSet.dataSet.get(3), dataSetVal.get(1));
        assertEquals(JobListDataSet.dataSet.get(1), dataSetVal.get(2));
        assertEquals(JobListDataSet.dataSet.get(0), dataSetVal.get(3));
    }

    @Test
    void getJobListWithoutFilterSortByJobType() {
        JobListRequest request = JobListRequest.builder()
                .sortField(JobListResponseModel.JOB_TYPE).build();
        JobListData data = JobListData.builder().build();
        doReturn(List.of(data)).when(mapper).map(any(), any());

        JobListResponse response = service.getJobList(request);

        verify(mapper, times(1)).map(jobListArgumentCaptor.capture(), any());
        assertEquals(200, response.getStatusCode());
        assertEquals(ResponseMessageEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(1, response.getTotalData());
        assertEquals(1, response.getData().size());
        assertEquals(data, response.getData().get(0));

        List<JobList> dataSetVal = jobListArgumentCaptor.getValue().toList();
        assertEquals(4, dataSetVal.size());
        assertEquals(JobListDataSet.dataSet.get(0), dataSetVal.get(0));
        assertEquals(JobListDataSet.dataSet.get(1), dataSetVal.get(1));
        assertEquals(JobListDataSet.dataSet.get(3), dataSetVal.get(2));
        assertEquals(JobListDataSet.dataSet.get(2), dataSetVal.get(3));
    }

    @Test
    void getJobListWithoutFilterSortByGender() {
        JobListRequest request = JobListRequest.builder()
                .sortField(JobListResponseModel.GENDER).build();
        JobListData data = JobListData.builder().build();
        doReturn(List.of(data)).when(mapper).map(any(), any());

        JobListResponse response = service.getJobList(request);

        verify(mapper, times(1)).map(jobListArgumentCaptor.capture(), any());
        assertEquals(200, response.getStatusCode());
        assertEquals(ResponseMessageEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(1, response.getTotalData());
        assertEquals(1, response.getData().size());
        assertEquals(data, response.getData().get(0));

        List<JobList> dataSetVal = jobListArgumentCaptor.getValue().toList();
        assertEquals(4, dataSetVal.size());
        assertEquals(JobListDataSet.dataSet.get(3), dataSetVal.get(0));
        assertEquals(JobListDataSet.dataSet.get(2), dataSetVal.get(1));
        assertEquals(JobListDataSet.dataSet.get(0), dataSetVal.get(2));
        assertEquals(JobListDataSet.dataSet.get(1), dataSetVal.get(3));
    }

    @Test
    void getJobListWithoutFilterSortByExperience() {
        JobListRequest request = JobListRequest.builder()
                .sortField(JobListResponseModel.EXPERIENCE).build();
        JobListData data = JobListData.builder().build();
        doReturn(List.of(data)).when(mapper).map(any(), any());

        JobListResponse response = service.getJobList(request);

        verify(mapper, times(1)).map(jobListArgumentCaptor.capture(), any());
        assertEquals(200, response.getStatusCode());
        assertEquals(ResponseMessageEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(1, response.getTotalData());
        assertEquals(1, response.getData().size());
        assertEquals(data, response.getData().get(0));

        List<JobList> dataSetVal = jobListArgumentCaptor.getValue().toList();
        assertEquals(4, dataSetVal.size());
        assertEquals(JobListDataSet.dataSet.get(1), dataSetVal.get(0));
        assertEquals(JobListDataSet.dataSet.get(0), dataSetVal.get(1));
        assertEquals(JobListDataSet.dataSet.get(3), dataSetVal.get(2));
        assertEquals(JobListDataSet.dataSet.get(2), dataSetVal.get(3));
    }

    @Test
    void getJobListWithoutFilterSortBySalary() {
        JobListRequest request = JobListRequest.builder()
                .sortField(JobListResponseModel.SALARY).build();
        JobListData data = JobListData.builder().build();
        doReturn(List.of(data)).when(mapper).map(any(), any());

        JobListResponse response = service.getJobList(request);

        verify(mapper, times(1)).map(jobListArgumentCaptor.capture(), any());
        assertEquals(200, response.getStatusCode());
        assertEquals(ResponseMessageEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(1, response.getTotalData());
        assertEquals(1, response.getData().size());
        assertEquals(data, response.getData().get(0));

        List<JobList> dataSetVal = jobListArgumentCaptor.getValue().toList();
        assertEquals(4, dataSetVal.size());
        assertEquals(JobListDataSet.dataSet.get(3), dataSetVal.get(0));
        assertEquals(JobListDataSet.dataSet.get(0), dataSetVal.get(1));
        assertEquals(JobListDataSet.dataSet.get(1), dataSetVal.get(2));
        assertEquals(JobListDataSet.dataSet.get(2), dataSetVal.get(3));
    }

    @Test
    void getJobListWithoutFilterSortByJobId() {
        JobListRequest request = JobListRequest.builder()
                .salaryInt(10000)
                .sortField(JobListResponseModel.JOB_ID)
                .sortType("desc").build();
        JobListData data = JobListData.builder().build();
        doReturn(List.of(data)).when(mapper).map(any(), any());

        JobListResponse response = service.getJobList(request);

        verify(mapper, times(1)).map(jobListArgumentCaptor.capture(), any());
        assertEquals(200, response.getStatusCode());
        assertEquals(ResponseMessageEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(1, response.getTotalData());
        assertEquals(1, response.getData().size());
        assertEquals(data, response.getData().get(0));

        List<JobList> dataSetVal = jobListArgumentCaptor.getValue().toList();
        assertEquals(4, dataSetVal.size());
        assertEquals(JobListDataSet.dataSet.get(2), dataSetVal.get(0));
        assertEquals(JobListDataSet.dataSet.get(3), dataSetVal.get(1));
        assertEquals(JobListDataSet.dataSet.get(0), dataSetVal.get(2));
        assertEquals(JobListDataSet.dataSet.get(1), dataSetVal.get(3));
    }

    @Test
    void getJobListWithoutSort() {
        JobListRequest request = mockRequest(null, null, null);
        JobListData data = JobListData.builder().build();
        doReturn(List.of(data)).when(mapper).map(any(), any());

        JobListResponse response = service.getJobList(request);

        verify(mapper, times(1)).map(jobListArgumentCaptor.capture(), any());
        assertEquals(200, response.getStatusCode());
        assertEquals(ResponseMessageEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(1, response.getTotalData());
        assertEquals(1, response.getData().size());
        assertEquals(data, response.getData().get(0));

        List<JobList> dataSetVal = jobListArgumentCaptor.getValue().toList();
        assertEquals(2, dataSetVal.size());
        assertEquals(JobListDataSet.dataSet.get(0), dataSetVal.get(0));
        assertEquals(JobListDataSet.dataSet.get(1), dataSetVal.get(1));
    }

    @Test
    void getJobListFilterMaxExperience() {
        JobListRequest request = JobListRequest.builder()
                .experienceInt(10).build();
        JobListData data = JobListData.builder().build();
        doReturn(List.of(data)).when(mapper).map(any(), any());

        JobListResponse response = service.getJobList(request);

        verify(mapper, times(1)).map(jobListArgumentCaptor.capture(), any());
        assertEquals(200, response.getStatusCode());
        assertEquals(ResponseMessageEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(1, response.getTotalData());
        assertEquals(1, response.getData().size());
        assertEquals(data, response.getData().get(0));

        List<JobList> dataSetVal = jobListArgumentCaptor.getValue().toList();
        assertEquals(1, dataSetVal.size());
        assertEquals(JobListDataSet.dataSet.get(2), dataSetVal.get(0));
    }

    @Test
    void getJobListFilterMinExperience() {
        JobListRequest request = JobListRequest.builder()
                .salaryInt(30000)
                .experienceInt(0).build();
        JobListData data = JobListData.builder().build();
        doReturn(List.of(data)).when(mapper).map(any(), any());

        JobListResponse response = service.getJobList(request);

        verify(mapper, times(1)).map(jobListArgumentCaptor.capture(), any());
        assertEquals(200, response.getStatusCode());
        assertEquals(ResponseMessageEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(1, response.getTotalData());
        assertEquals(1, response.getData().size());
        assertEquals(data, response.getData().get(0));

        List<JobList> dataSetVal = jobListArgumentCaptor.getValue().toList();
        assertEquals(2, dataSetVal.size());
        assertEquals(JobListDataSet.dataSet.get(0), dataSetVal.get(0));
        assertEquals(JobListDataSet.dataSet.get(1), dataSetVal.get(1));
    }

    @Test
    void getJobListWithEmptyResult() {
        JobListRequest request = mockRequest("TEMP_SA", null, null);
        request.setExperienceInt(11);
        request.setSalaryInt(100000);
        doReturn(Collections.emptyList()).when(mapper).map(any(), any());

        JobListResponse response = service.getJobList(request);

        verify(mapper, times(1)).map(jobListArgumentCaptor.capture(), any());
        assertEquals(200, response.getStatusCode());
        assertEquals(ResponseMessageEnum.SUCCESS.getMessage(), response.getMessage());
        assertEquals(0, response.getTotalData());
        assertNull(response.getData());

        List<JobList> dataSetVal = jobListArgumentCaptor.getValue().toList();
        assertTrue(dataSetVal.isEmpty());
    }

    private JobListRequest mockRequest(String jobId,
                                       String sortField,
                                       String sortType) {
        return JobListRequest.builder()
                .jobId(jobId)
                .jobTitle("Engineer")
                .jobTag("it")
                .gender("N")
                .salaryInt(20000)
                .experienceInt(1)
                .jobType("PERM")
                .sortField(sortField)
                .sortType(sortType).build();
    }
}

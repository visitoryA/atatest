package com.backend.atatest.service;

import com.backend.atatest.constant.CommonConstant;
import com.backend.atatest.constant.DeclaredFields;
import com.backend.atatest.constant.ResponseMessageEnum;
import com.backend.atatest.dataset.JobListDataSet;
import com.backend.atatest.mapper.JobListMapper;
import com.backend.atatest.model.JobList;
import com.backend.atatest.model.JobListRequest;
import com.backend.atatest.model.JobListData;
import com.backend.atatest.model.JobListResponse;
import com.backend.atatest.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class JobListService {
    private JobListMapper jobMapper;

    public JobListResponse getJobList(JobListRequest request) {
        Stream<JobList> dataSet = JobListDataSet.dataSet.stream();
        // filter data row
        dataSet = filterData(dataSet, request);

        // sort data
        if (Strings.isNotBlank(request.getSortField())) {
            ArrayList<JobList> jobList = new ArrayList<>(dataSet.toList());
            sortData(jobList, request.getSortField(), request.getSortType());
            dataSet = jobList.stream();
        }

        // filter column
        List<JobListData> data = jobMapper.map(dataSet, request.getFieldStr());

        return JobListResponse.builder()
                .statusCode(ResponseMessageEnum.SUCCESS.getHttpStatus().value())
                .message(ResponseMessageEnum.SUCCESS.getMessage())
                .totalData(data.size())
                .data(data.isEmpty() ? null : data)
                .build();
    }

    private Stream<JobList> filterData(Stream<JobList> data, JobListRequest request) {
        if (request.getJobTitle() != null) {
            data = filterByJobTitle(data, request.getJobTitle());
        }
        if (request.getJobId() != null) {
            data = filterByJobId(data, request.getJobId());
        }
        if (request.getGender() != null) {
            data = filterByGender(data, request.getGender());
        }
        if (request.getJobType() != null) {
            data = filterByJobType(data, request.getJobType());
        }
        if (request.getSalaryInt() != null) {
            data = filterBySalary(data, request.getSalaryInt());
        }
        if (request.getExperienceInt() != null) {
            data = filterByExp(data, request.getExperienceInt());
        }
        if (request.getJobTag() != null) {
            data = filterByJobTag(data, request.getJobTag());
        }
        return data;
    }

    private Stream<JobList> filterByJobTitle(Stream<JobList> data, String jobTitle) {
        String[] titleWords = jobTitle.split(CommonConstant.SPACE);
        String titleRegex = "^.*(" + String.join(CommonConstant.PIPE, titleWords) + ").*$";
        return data.filter(it -> ValidatorUtil.validateWithRegex(titleRegex, it.getJobTitle()));
    }
    private Stream<JobList> filterByJobId(Stream<JobList> data, String jobId) {
        return data.filter(it -> jobId.equalsIgnoreCase(it.getJobId()));
    }
    private Stream<JobList> filterByGender(Stream<JobList> data, String gender) {
        return data.filter(it -> gender.equalsIgnoreCase(it.getGender().name()));
    }
    private Stream<JobList> filterByJobType(Stream<JobList> data, String jobType) {
        return data.filter(it -> jobType.equalsIgnoreCase(it.getJobType().name()));
    }
    private Stream<JobList> filterBySalary(Stream<JobList> data, Integer salary) {
        return data.filter(it -> {
            int minSalary = Integer.parseInt(it.getSalary().split(" - ")[0]);
            return salary <= minSalary;
        });
    }
    private Stream<JobList> filterByJobTag(Stream<JobList> data, String tag) {
        return data.filter(it -> it.getJobTag().stream().anyMatch(item -> item.equalsIgnoreCase(tag)));
    }
    private Stream<JobList> filterByExp(Stream<JobList> data, Integer exp) {
        return data.filter(it -> {
            String[] expArray = it.getExperience().split(" - ");
            int minExp = Integer.parseInt(expArray[0]);
            int maxExp = Integer.parseInt(expArray[1]);
            return minExp <= exp && exp <= maxExp;
        });
    }

    private void sortData(List<JobList> data, String fieldName, String sortType) {
        Comparator<JobList> comparator = switch (fieldName) {
            case DeclaredFields.JobListResponseModel.JOB_TITLE -> Comparator.comparing(JobList::getJobTitle);
            case DeclaredFields.JobListResponseModel.JOB_TYPE -> Comparator.comparing(JobList::getJobType);
            case DeclaredFields.JobListResponseModel.GENDER -> Comparator.comparing(JobList::getGender);
            case DeclaredFields.JobListResponseModel.EXPERIENCE -> Comparator.comparing(JobList::getExperience);
            case DeclaredFields.JobListResponseModel.SALARY -> Comparator.comparing(JobList::getSalary);
            default -> Comparator.comparing(JobList::getJobId);
        };
        if (CommonConstant.SORT_TYPE.DESC.equalsIgnoreCase(sortType)) {
            comparator = comparator.reversed();
        }
        data.sort(comparator);
    }
}

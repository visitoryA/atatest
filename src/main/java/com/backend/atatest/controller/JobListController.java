package com.backend.atatest.controller;

import com.backend.atatest.constant.*;
import com.backend.atatest.exception.BusinessException;
import com.backend.atatest.model.JobListRequest;
import com.backend.atatest.model.JobListResponse;
import com.backend.atatest.service.JobListService;
import com.backend.atatest.util.CommonUtil;
import com.backend.atatest.util.ValidatorUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;

import static com.backend.atatest.constant.CommonConstant.JSON_CONTENT_TYPE;
import static com.backend.atatest.constant.CommonConstant.REGEX.NUM_FORMAT;
import static com.backend.atatest.constant.DeclaredFields.*;
import static com.backend.atatest.constant.DeclaredFields.JobListResponseModel.*;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/v1")
public class JobListController {
    private JobListService jobListService;

    @GetMapping(value = "/job-list", produces = JSON_CONTENT_TYPE)
    public ResponseEntity<JobListResponse> getJobList(
            @RequestParam(value = JOB_ID, required = false) String jobId,
            @RequestParam(value = JOB_TITLE, required = false) String jobTitle,
            @RequestParam(value = JOB_TYPE, required = false) String jobType,
            @RequestParam(value = JOB_TAG, required = false) String jobTag,
            @RequestParam(value = GENDER, required = false) String gender,
            @RequestParam(value = SALARY, required = false) String salary,
            @RequestParam(value = EXPERIENCE, required = false) String exp,
            @RequestParam(value = FIELDS, required = false) List<String> fields,
            @RequestParam(value = SORT, required = false) String sortField,
            @RequestParam(value = SORT_TYPE, required = false) String sortType
    ) throws BusinessException {
        JobListRequest request = JobListRequest.builder()
                .jobId(jobId)
                .jobTitle(jobTitle)
                .jobType(jobType)
                .jobTag(jobTag)
                .gender(gender)
                .salary(salary)
                .experience(exp)
                .fieldStr(fields)
                .sortField(sortField)
                .sortType(sortType)
                .build();

        checkMissingRequest(request);
        validateAndFormatRequest(request);
        return ResponseEntity.ok(jobListService.getJobList(request));
    }

    private void checkMissingRequest(JobListRequest request) throws BusinessException {
        StringBuilder missingFields = new StringBuilder();

        if (request.getSortField() == null && request.getSortType() != null) {
            CommonUtil.appendStrBuilder(missingFields, SORT);
        }

        if (!missingFields.isEmpty()) {
            throw new BusinessException(ResponseMessageEnum.MISSING_REQ, missingFields.toString());
        }
    }

    private void validateAndFormatRequest(JobListRequest request) throws BusinessException {
        StringBuilder invalidFields = new StringBuilder();

        JobTypeEnum.validate(request, invalidFields);
        GenderEnum.validate(request, invalidFields);

        if (request.getSalary() != null) {
            if (ValidatorUtil.validateWithRegex(NUM_FORMAT, request.getSalary())) {
                request.setSalaryInt(CommonUtil.convertStrToInt(request.getSalary()));
            } else {
                CommonUtil.appendStrBuilder(invalidFields, SALARY);
            }
        }

        if (request.getExperience() != null) {
            if (ValidatorUtil.validateWithRegex(NUM_FORMAT, request.getExperience())) {
                request.setExperienceInt(CommonUtil.convertStrToInt(request.getExperience()));
            } else {
                CommonUtil.appendStrBuilder(invalidFields, EXPERIENCE);
            }
        }

        Field[] declaredFields = DeclaredFields.JobListResponseModel.class.getDeclaredFields();
        if (!ObjectUtils.isEmpty(request.getFieldStr()) &&
                ValidatorUtil.invalidFieldName(request.getFieldStr(), declaredFields)) {
            CommonUtil.appendStrBuilder(invalidFields, FIELDS);
        }

        if ((request.getSortField() != null && ValidatorUtil.invalidFieldName(List.of(request.getSortField()), declaredFields))) {
            CommonUtil.appendStrBuilder(invalidFields, SORT);
        }
        if (request.getSortField() != null && request.getSortType() == null) {
            request.setSortType(CommonConstant.SORT_TYPE.ASC);
        }
        if (request.getSortType() != null &&
                !ValidatorUtil.validateWithRegex(CommonConstant.REGEX.SORT_TYPE, request.getSortType().toUpperCase(Locale.ROOT))) {
            CommonUtil.appendStrBuilder(invalidFields, SORT_TYPE);
        }

        if (!invalidFields.isEmpty()) {
            throw new BusinessException(ResponseMessageEnum.INVALID_REQ, invalidFields.toString());
        }
    }
}

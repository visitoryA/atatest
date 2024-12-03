package com.backend.atatest.util;

import com.backend.atatest.constant.DeclaredFields.JobListResponseModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.List;

import static com.backend.atatest.constant.CommonConstant.REGEX.NUM_FORMAT;
import static com.backend.atatest.constant.CommonConstant.REGEX.SORT_TYPE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ValidatorUtilTest {

    @Test
    void validateWithRegexWithNumber() {
        assertTrue(ValidatorUtil.validateWithRegex(NUM_FORMAT, "122.0"));
        assertTrue(ValidatorUtil.validateWithRegex(NUM_FORMAT, "121,234.00"));
        assertTrue(ValidatorUtil.validateWithRegex(NUM_FORMAT, "123,456,789"));
        assertTrue(ValidatorUtil.validateWithRegex(NUM_FORMAT, "1000"));
        assertTrue(ValidatorUtil.validateWithRegex(NUM_FORMAT, "45678.99"));

        assertFalse(ValidatorUtil.validateWithRegex(NUM_FORMAT, "112.0.0"));
        assertFalse(ValidatorUtil.validateWithRegex(NUM_FORMAT, "12,1234.00"));
    }

    @Test
    void validateWithRegexWithSortType() {
        assertTrue(ValidatorUtil.validateWithRegex(SORT_TYPE, "AsC"));
        assertTrue(ValidatorUtil.validateWithRegex(SORT_TYPE, "DESC"));

        assertFalse(ValidatorUtil.validateWithRegex(SORT_TYPE, "asc|desc"));
        assertFalse(ValidatorUtil.validateWithRegex(SORT_TYPE, "unmatch"));
    }

    @Test
    void invalidFieldNameWorksProperly() {
        Field[] declaredFields = JobListResponseModel.class.getDeclaredFields();
        List<String> invalidFields = List.of(JobListResponseModel.JOB_TITLE, "invalid_field");
        List<String> validFields = List.of(JobListResponseModel.JOB_TITLE, JobListResponseModel.EXPERIENCE);

        assertTrue(ValidatorUtil.invalidFieldName(invalidFields, declaredFields));
        assertFalse(ValidatorUtil.invalidFieldName(validFields, declaredFields));
    }
}

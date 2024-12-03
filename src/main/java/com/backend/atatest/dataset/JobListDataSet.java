package com.backend.atatest.dataset;

import com.backend.atatest.constant.GenderEnum;
import com.backend.atatest.constant.JobTypeEnum;
import com.backend.atatest.model.JobList;

import java.util.List;

public final class JobListDataSet {
    public static final List<JobList> dataSet = List.of(
            new JobList("JRPERM_SE", "Software Engineer", JobTypeEnum.PERM, GenderEnum.N, "", "30000 - 45000", "0 - 3", List.of("Developer", "IT", "Permanent")),
            new JobList("JRPERM_QA", "QA Engineer", JobTypeEnum.PERM, GenderEnum.N, "", "30000 - 55000", "0 - 2", List.of("Automated", "Test", "IT", "Permanent")),
            new JobList("TEMP_SA", "Contract Solution Architect", JobTypeEnum.TEMP, GenderEnum.M, "", "60000 - 90000", "5 - 10", List.of("Solution", "IT", "Temporary", "Contract")),
            new JobList("PT_QA", "Part-Time QA Engineer", JobTypeEnum.PARTTIME, GenderEnum.F, "", "20000 - 50000", "1 - 4", List.of("Automated", "Test", "IT", "Part-Time", "Manual"))
    );
}

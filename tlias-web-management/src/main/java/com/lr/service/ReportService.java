package com.lr.service;

import com.lr.pojo.JobOption;
import com.lr.pojo.StuNumCount;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各个职位的员工人数
     * @return
     */
    JobOption getEmpJobData();


    List<Map<String,Object>> getEmpGenderData();

    StuNumCount getStudentCountData();

    List<Map<String, Object>> getStudentDegreeData();
}

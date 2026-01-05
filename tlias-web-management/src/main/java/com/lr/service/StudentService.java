package com.lr.service;

import com.lr.pojo.PageResult;
import com.lr.pojo.Student;

import java.util.List;

public interface StudentService {
    PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize);

    void save(Student student);

    Student getInfo(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void updateViolation(Integer id, Short score);
}

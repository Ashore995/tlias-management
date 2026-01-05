package com.lr.service;

import com.lr.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

    void deleteById(Integer id);

    void insert(Dept dept);

    Dept getInfo(Integer id);

    void update(Dept dept);
}

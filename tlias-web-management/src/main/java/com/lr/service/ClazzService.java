package com.lr.service;

import com.lr.pojo.Clazz;
import com.lr.pojo.ClazzQueryParam;
import com.lr.pojo.PageResult;

import java.util.List;


public interface ClazzService {

    PageResult  page(ClazzQueryParam clazzQueryParam);

    void save(Clazz clazz);

    Clazz getInfo(Integer id);

    void update(Clazz clazz);

    void delete(Integer id);

    List<Clazz> list();
}
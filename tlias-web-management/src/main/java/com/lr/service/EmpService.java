package com.lr.service;

import com.lr.pojo.Emp;
import com.lr.pojo.EmpQueryParam;
import com.lr.pojo.LoginInfo;
import com.lr.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    /**
     * 根据ID查询员工的详细信息
     */
    Emp getInfo(Integer id);

    void update(Emp emp);

    List<Emp> list();

    LoginInfo login(String username, String password);
}

package com.lr.service;

import com.lr.pojo.OperateLog;
import com.lr.pojo.PageResult;

import java.util.List;

public interface LogService {

    PageResult page(Integer page, Integer pageSize);

}

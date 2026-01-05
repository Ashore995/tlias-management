package com.lr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lr.mapper.LogMapper;
import com.lr.pojo.OperateLog;
import com.lr.pojo.PageResult;
import com.lr.pojo.Student;
import com.lr.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;
    @Override
    public PageResult page(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<OperateLog> operateLogs =logMapper.list();
        Page<OperateLog> p=(Page<OperateLog>)operateLogs;
        PageResult pageResult=new PageResult(p.getTotal(),p.getResult());
        return pageResult;
    }
}

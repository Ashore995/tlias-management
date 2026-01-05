package com.lr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lr.mapper.EmpExprMapper;
import com.lr.mapper.EmpMapper;
import com.lr.pojo.*;
import com.lr.service.EmpLogService;
import com.lr.service.EmpService;
import com.lr.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private EmpExprMapper empExprMapper;
    @Autowired
    private EmpLogService empLogService;

    @Override
    public PageResult page(EmpQueryParam empQueryParam) {
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());
        List<Emp> empList = empMapper.list(empQueryParam);
        Page<Emp> p=(Page<Emp>)empList;
        return new PageResult(p.getTotal(),p.getResult());
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());
            empMapper.insert(emp);
            List<EmpExpr> exprList = emp.getExprList();
            Integer empId = emp.getId();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(expr -> expr.setEmpId(empId));
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null, LocalDateTime.now(), emp.toString());
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        empMapper.deleteByIds(ids);//删除员工基本信息

        empExprMapper.deleteByEmpIds(ids);//删除员工expr信息
    }

    @Override
    public Emp getInfo(Integer id) {
        return empMapper.getById(id);
    }

    @Override
    public void update(Emp emp) {
        //1. 根据ID更新员工基本信息
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //2. 根据员工ID删除员工的工作经历信息 【删除老的】,从数据库中直接删除
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

        //3. 新增员工的工作经历数据 【新增新的】，从前端传过来的json中添加
        Integer empId = emp.getId();
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            //前端往后传数据的时候没有传递empId，所以要在后端设置一下
            exprList.forEach(empExpr -> empExpr.setEmpId(empId));
            empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> list() {
        return empMapper.findAll();
    }

    @Override
    public LoginInfo login(String username, String password) {
        Emp emp = empMapper.getByUsernameAndPassword(username,password);
        if(emp!=null){
//            生成jwt令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",emp.getId());
            claims.put("username",emp.getUsername());
            String jwt = JwtUtils.generateJwt(claims);
            return new LoginInfo(emp.getId(),emp.getUsername(),emp.getName(),jwt);
        }
        return null;
    }

}

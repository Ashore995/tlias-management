package com.lr.mapper;

import com.lr.pojo.OperateLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Mapper
public interface LogMapper {
    @Select("select o.*,e.name operateEmpName from operate_log o left join emp e on o.operate_emp_id=e.id")
    List<OperateLog> list();
}

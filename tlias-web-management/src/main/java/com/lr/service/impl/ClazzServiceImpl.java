package com.lr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lr.exception.BusinessException;
import com.lr.mapper.ClazzMapper;
import com.lr.pojo.Clazz;
import com.lr.pojo.ClazzQueryParam;
import com.lr.pojo.Emp;
import com.lr.pojo.PageResult;
import com.lr.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    @Override
    public PageResult page(ClazzQueryParam clazzQueryParam) {
        PageHelper.startPage(clazzQueryParam.getPage(),clazzQueryParam.getPageSize());
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
//        根据当前时间与 beginDate和endDate比较，设置status
        clazzList.forEach(clazz -> {
            if(clazz.getBeginDate()!=null && clazz.getEndDate()!=null){
                if(clazz.getBeginDate().isBefore(LocalDate.now()) && clazz.getEndDate().isAfter(LocalDate.now())){
                    clazz.setStatus("已开班");
                }else if(clazz.getBeginDate().isAfter(LocalDate.now())){
                    clazz.setStatus("未开班");
                }else if(clazz.getEndDate().isBefore(LocalDate.now())){
                    clazz.setStatus("已结课");
                }
            }
        });


        Page<Clazz> p=(Page<Clazz>)clazzList;
        return new PageResult(p.getTotal(),p.getResult());
    }

    @Override
    public void save(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    @Override
    public Clazz getInfo(Integer id) {
        Clazz clazz = clazzMapper.getById(id);
        return clazz;
    }

    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.update(clazz);
    }

    @Override
    public void delete(Integer id) {
        //如果班级下面有学生，不允许删除
        Integer i = clazzMapper.StudentNumByClazz(id);
        if(i >0){
            throw new BusinessException("班级下有学员, 不能直接删除~");
        }else{
            clazzMapper.delete(id);
        }
    }

    @Override
    public List<Clazz> list() {
        return clazzMapper.findAll();
    }
}

package com.lr.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lr.mapper.StudentMapper;
import com.lr.pojo.PageResult;
import com.lr.pojo.Student;
import com.lr.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public PageResult page(String name, Integer degree, Integer clazzId, Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<Student> studentList =studentMapper.list(name, degree, clazzId);
        Page<Student> p=(Page<Student>)studentList;
        PageResult pageResult=new PageResult(p.getTotal(),p.getResult());
        return pageResult;
    }

    @Override
    public void save(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getInfo(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void updateViolation(Integer id, Short score) {
        Student stu = studentMapper.getById(id);
        stu.setViolationCount((short) (stu.getViolationCount()+1));
        stu.setViolationScore((short) (stu.getViolationScore()+score));
        studentMapper.updateViolation(stu);
    }
}

package com.lr.mapper;

import com.lr.pojo.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    List<Student> list(String name, Integer degree, Integer clazzId);

    void insert(Student student);

    @Select("select * from student where id = #{id}")
    Student getById(Integer id);

    void update(Student student);

    void delete(List<Integer> ids);

    void updateViolation(Student stu);

    @MapKey("name")
    List<Map<String, Object>> countStudentData();

    @MapKey("name")
    List<Map<String, Object>> countStuDegreeData();
}

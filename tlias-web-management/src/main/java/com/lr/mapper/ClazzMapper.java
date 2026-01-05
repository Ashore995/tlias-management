package com.lr.mapper;

import com.lr.pojo.Clazz;
import com.lr.pojo.ClazzQueryParam;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ClazzMapper {
    List<Clazz> list(ClazzQueryParam clazzQueryParam);

    void insert(Clazz clazz);

    @Select("select c.*,e.name masterName from clazz c left join emp e on c.master_id=e.id where c.id=#{id}")
    Clazz getById(Integer id);

    void update(Clazz clazz);

    @Delete("delete from clazz where id=#{id}")
    void delete(Integer id);

    @Select("select * from clazz")
    List<Clazz> findAll();

    @Select("select count(s.id) from clazz c left join student s on c.id = s.clazz_id where c.id = #{id}")
    Integer StudentNumByClazz(Integer id);
}

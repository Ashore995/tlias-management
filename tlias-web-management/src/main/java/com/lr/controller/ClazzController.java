package com.lr.controller;

import com.lr.pojo.Clazz;
import com.lr.pojo.ClazzQueryParam;
import com.lr.pojo.PageResult;
import com.lr.pojo.Result;
import com.lr.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    /**
     * 查询所有班级信息
     * @return
     */
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){
        log.info("查询所有班级信息:{}",clazzQueryParam);
        PageResult pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 添加班级信息
     * @return
     */
    @PostMapping
    public Result save(@RequestBody Clazz clazz){
        log.info("添加班级信息:{}",clazz);
        clazzService.save(clazz);
        return Result.success();
    }

    /**
     * 根据id查询班级信息
     * @return
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询班级信息:{}",id);
        Clazz clazz = clazzService.getInfo(id);
        return Result.success(clazz);
    }

    /**
     * 修改班级信息
     * @return
     */
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级信息:{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }

    /**
     * 删除班级信息
     * @return
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级信息:{}",id);
        clazzService.delete(id);
        return Result.success();
    }

    /**
     * 查询所有班级信息
     * @return
     */
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级信息");
        List<Clazz> clazzList = clazzService.list();
        return Result.success(clazzList);
    }
}

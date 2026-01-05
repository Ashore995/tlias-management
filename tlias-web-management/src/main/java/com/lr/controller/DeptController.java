package com.lr.controller;

import com.lr.anno.Log;
import com.lr.pojo.Dept;
import com.lr.pojo.Result;
import com.lr.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {

    //private static final Logger log = LoggerFactory.getLogger(DeptController.class);

    @Autowired
    private DeptService deptService;

    //@RequestMapping(value = "/depts",method = RequestMethod.GET)
    @GetMapping
    public Result list(){
        log.info("查询全部部门数据");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    @Log
    @DeleteMapping
    public Result deleteById(Integer id){
        log.info("根据ID删除部门: {}",id);
        deptService.deleteById(id);
        return Result.success();
    }

    @Log
    @PostMapping
    public Result insert(@RequestBody Dept dept){
        log.info("新增部门: {}",dept);
        deptService.insert(dept);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("根据id查询数据: {}",id);
        Dept dept = deptService.getInfo(id);
        return Result.success(dept);
    }

    @Log
    @PutMapping
    public Result update(@RequestBody Dept dept){
        log.info("修改部门: {}",dept);
        deptService.update(dept);
        return Result.success();
    }



}

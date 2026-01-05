package com.lr.controller;

import com.lr.pojo.PageResult;
import com.lr.pojo.Result;
import com.lr.pojo.Student;
import com.lr.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(String name, Integer degree, Integer clazzId,
                       @RequestParam(defaultValue = "1") Integer page,
                       @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("查询学生信息, name={}, degree={}, clazzId={}, page={}, pageSize={}", name, degree, clazzId, page, pageSize);
        PageResult stuPage = studentService.page(name, degree, clazzId, page, pageSize);
        return Result.success(stuPage);
    }

    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("保存学生信息, student={}", student);
        studentService.save(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("查询学生信息, id={}", id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }

    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("更新学生信息, student={}", student);
        studentService.update(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("删除学生信息, ids={}", ids);
        studentService.delete(ids);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable Integer id, @PathVariable Short score) {
        log.info("更新学生违规信息, id={}, score={}", id, score);
        studentService.updateViolation(id, score);
        return Result.success();
    }

}

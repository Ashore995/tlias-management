package com.lr.controller;

import com.lr.pojo.Emp;
import com.lr.pojo.LoginInfo;
import com.lr.pojo.Result;
import com.lr.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private EmpService empService;
    @PostMapping("/login")
    public Result login(@RequestBody Emp emp) {
        log.info("用户名密码：{}", emp);
        LoginInfo loginInfo = empService.login(emp.getUsername(), emp.getPassword());
        if(loginInfo==null){
            return Result.error("用户名或密码错误");
        }
        return Result.success(loginInfo);
    }
}

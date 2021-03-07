package com.example.shardingJdbc.controller;

import com.example.shardingJdbc.entity.User;
import com.example.shardingJdbc.service.IUserService;
import io.shardingsphere.api.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @PutMapping("/save")
    public Object save() {
        User user = new User();
        user.setName("新增");
        return userService.save(user);
    }

    @GetMapping("/list")
    public Object list() {
        // 强制路由主库
//        HintManager.getInstance().setMasterRouteOnly();
        return userService.list();
    }

}

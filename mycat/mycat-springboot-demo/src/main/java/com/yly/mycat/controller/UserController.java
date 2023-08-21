package com.yly.mycat.controller;

;
import com.yly.mycat.domain.User;
import com.yly.mycat.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by wolfcode-lanxw
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/save")
    public String save(User user) {
        userMapper.insert(user);
        return "保存成功";
    }

    @RequestMapping("/list")
    public List<User> list() {
        return userMapper.selectAll();
    }
}

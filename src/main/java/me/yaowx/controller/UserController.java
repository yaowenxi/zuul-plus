package me.yaowx.controller;

import me.yaowx.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Yaowx
 * @email worthyyao@qq.com
 * @Date: 2021/12/1
 */
@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @GetMapping("users")
    public Object users() {
        return userDao.findAll();
    }
}

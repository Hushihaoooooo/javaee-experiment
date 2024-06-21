package com.shihaohu.controller;


import com.mysql.cj.util.StringUtils;
import com.shihaohu.mapper.UserMapper;
import com.shihaohu.model.Result;
import com.shihaohu.model.User;
import com.shihaohu.service.UserService;
import com.shihaohu.utils.JwtUtil;
import com.shihaohu.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shihaohu
 * @date 2024/6/21 11:56
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        System.out.println(user);
        User u = userService.findByUsername(user.getUserName());
        if (u == null) {
            userService.register(user.getUserName(), user.getPassword());
            return Result.success();
        }else {
            return Result.error("用户名已经被占用");
        }
    }

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        System.out.println(user);
        User u = userService.findByUsername(user.getUserName());
        if (u == null) {
            return Result.error("用户名错误");
        }

        if (Md5Util.checkPassword(user.getPassword(), u.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("userName", u.getUserName());
            claims.put("id", u.getId());
            String token = JwtUtil.getToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误");
    }

    @GetMapping("/userInfo")
    public Result getUserInfo(@RequestHeader("Authorization") String token) {
        Map<String, Object> map = JwtUtil.parseToken(token);
        String userName = (String) map.get("userName");
        System.out.println(userName);
        User u = userService.findByUsername(userName);
        return Result.success(u);
    }

    @PostMapping("/update")
    public Result update(@RequestBody User user) {
        System.out.println("user/update" + user);
        User u = userService.findByUsername(user.getUserName());
        if (u != null && !u.getId().equals(user.getId())) {
            return Result.error("该用户名已被占用");
        }
        userService.updateUserInfo(user);
        return Result.success();
    }

    @PostMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        String oldPwd = params.get("oldPwd");
        String newPwd = params.get("newPwd");
        String rePwd = params.get("rePwd");
        if (oldPwd.isEmpty() || newPwd.isEmpty() || rePwd.isEmpty()) {
            return Result.error("缺少必要参数");
        }
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次输入密码不正确");
        }
        Map<String, Object> map = JwtUtil.parseToken(token);
        String userName = (String) map.get("userName");
        User u = userService.findByUsername(userName);
        System.out.println(u.getPassword());
        if (!Md5Util.checkPassword(oldPwd, u.getPassword())) {
            return Result.error("原密码不正确");
        }
        userService.updatePwd(userName, newPwd);
        return Result.success();
    }



}

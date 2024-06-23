package com.shihaohu.service.Impl;

import com.shihaohu.mapper.UserMapper;
import com.shihaohu.model.User;
import com.shihaohu.service.UserService;
import com.shihaohu.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Shihaohu
 * @date 2024/6/21 12:00
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    UserMapper userMapper;

    @Override
    public User findByUsername(String userName) {
        User u = userMapper.findByUserName(userName);
        return u;
    }

    @Override
    public void register(String userName, String password) {
        String Md5Pwd = Md5Util.getMD5String(password);
        userMapper.add(userName, Md5Pwd);
    }

    @Override
    public void updateUserInfo(User user) {
        System.out.println("updateUserInfo" + user);
        userMapper.updateUserInfo(user);
    }

    @Override
    public void updatePwd(String userName, String newPwd) {
        String Md5Pwd = Md5Util.getMD5String(newPwd);
        userMapper.updatePwd(userName, Md5Pwd);
    }

    @Override
    public User select(int userId) {
        User u = userMapper.select(userId);
        return u;
    }
}

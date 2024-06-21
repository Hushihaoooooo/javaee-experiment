package com.shihaohu.service.Impl;

import com.shihaohu.mapper.UserMapper;
import com.shihaohu.model.User;
import com.shihaohu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Shihaohu
 * @date 2024/6/21 12:00
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Override
    public User findByUsername(String userName) {
        User u = userMapper.findByUserName(userName);
        return u;
    }

    @Override
    public void register(String userName, String password) {
        userMapper.add(userName, password);
    }

    @Override
    public void changeUserInfo(User user) {

    }
}

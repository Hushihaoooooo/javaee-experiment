package com.shihaohu.service;

import com.shihaohu.model.User;

/**
 * @author Shihaohu
 * @date 2024/6/21 11:59
 */
public interface UserService {

    User findByUsername(String userName);

    void register(String userName, String password);

    void updateUserInfo(User user);

    void updatePwd(String userName, String newPwd);
}
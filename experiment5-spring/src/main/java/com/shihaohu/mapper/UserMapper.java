package com.shihaohu.mapper;

import com.shihaohu.model.User;
import com.shihaohu.vo.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByUserName(String userName);

    void add(@Param("userName") String userName, @Param("password") String password);

    void updateUserInfo(@Param("user") User user);

    void updatePwd(@Param("userName") String userName, @Param("newPwd") String newPwd);

    User select(@Param("userId") int userId);
}

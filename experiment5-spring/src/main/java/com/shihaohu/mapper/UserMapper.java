package com.shihaohu.mapper;

import com.shihaohu.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    User findByUserName(String userName);

    void add(@Param("userName") String userName, @Param("password") String password);
}

package com.shihaohu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ConcernMapper {

    void addConcern(@Param("followerId") Integer followerId, @Param("followingId") Integer followingId);

    void deleteConcern(@Param("followerId") Integer followerId, @Param("followingId") Integer followingId);

    Integer checkConcern(Integer followerId, Integer followingId);
}

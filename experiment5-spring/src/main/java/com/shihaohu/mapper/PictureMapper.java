package com.shihaohu.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author Shihaohu
 * @date 2024/6/22 19:46
 */
@Mapper
public interface PictureMapper {
    void upload(@Param("uploadedBy") String uploadedBy, @Param("fileName") String fileName, @Param("customFileName") String customFileName);
}

package com.shihaohu.service;

import com.shihaohu.model.Picture;

import java.util.List;

/**
 * @author Shihaohu
 * @date 2024/6/22 19:45
 */
public interface PictureService {
    void upload(String uploadedBy, String fileName, String customFileName);

    List<Picture> getList(Integer userId);

    List<Picture> getAll();
}

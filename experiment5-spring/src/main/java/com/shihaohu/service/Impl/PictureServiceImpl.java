package com.shihaohu.service.Impl;

import com.shihaohu.mapper.PictureMapper;
import com.shihaohu.mapper.UserMapper;
import com.shihaohu.model.Picture;
import com.shihaohu.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Shihaohu
 * @date 2024/6/22 19:46
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureService {
    @Resource
    PictureMapper pictureMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public void upload(String uploadedBy, String fileName, String customFileName) {
        pictureMapper.upload(uploadedBy, fileName, customFileName);
    }

    @Override
    public List<Picture> getList(Integer userId) {
        List<Picture> pictures = userMapper.getList(userId);
        return pictures;
    }

    @Override
    public List<Picture> getAll() {
        List<Picture> pictures = userMapper.getAll();
        return pictures;
    }
}

package com.shihaohu.service.Impl;

import com.shihaohu.mapper.PictureMapper;
import com.shihaohu.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Shihaohu
 * @date 2024/6/22 19:46
 */
@Service
@Transactional
public class PictureServiceImpl implements PictureService {
    @Resource
    PictureMapper pictureMapper;

    @Override
    public void upload(String uploadedBy, String fileName, String customFileName) {
        pictureMapper.upload(uploadedBy, fileName, customFileName);
    }
}

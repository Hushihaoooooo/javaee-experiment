package com.shihaohu.service.Impl;

import com.shihaohu.mapper.ConcernMapper;
import com.shihaohu.service.ConcernService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class ConcernServiceImpl implements ConcernService {
    @Resource
    private ConcernMapper concernMapper;

    @Override
    public void addConcern(Integer followerId, Integer followingId) {
        concernMapper.addConcern(followerId, followingId);
    }

    @Override
    public void deleteConcern(Integer followerId, Integer followingId) {
        concernMapper.deleteConcern(followerId, followingId);
    }

    @Override
    public Boolean checkConcern(Integer followerId, Integer followingId) {
        Integer count = concernMapper.checkConcern(followerId, followingId);
        return count >= 1;
    }
}

package com.shihaohu.service;

public interface ConcernService {
    void addConcern(Integer followerId, Integer followingId);

    void deleteConcern(Integer followerId, Integer followingId);

    Boolean checkConcern(Integer followerId, Integer followingId);
}

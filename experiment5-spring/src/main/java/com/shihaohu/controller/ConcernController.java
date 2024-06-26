package com.shihaohu.controller;

import com.shihaohu.model.Result;
import com.shihaohu.service.ConcernService;
import com.shihaohu.utils.JwtUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shihaohu
 * @date 2024/6/26 15:01
 */
@RestController
@RequestMapping("/concern")
public class ConcernController {
    @Resource
    private ConcernService concernService;

    @GetMapping("/add")
    public Result addConcern(@RequestHeader("Authorization") String token, @RequestParam("followingId") Integer followingId) {
        Map<String, Object> map = JwtUtil.parseToken(token);
        Integer followerId = (Integer) map.get("id");
        concernService.addConcern(followerId, followingId);
        return Result.success();
    }

    @GetMapping("/check")
    public Result checkConcern(@RequestHeader("Authorization") String token, @RequestParam("followingId") Integer followingId) {
        Map<String, Object> map = JwtUtil.parseToken(token);
        Integer followerId = (Integer) map.get("id");
        Boolean flag = concernService.checkConcern(followerId, followingId);
        Map<String, Object> m = new HashMap<>();
        m.put("flag", flag.toString());
        System.out.println(m);
        return Result.success(m);
    }

    @GetMapping("/delete")
    public Result deleteConcern(@RequestHeader("Authorization") String token, @RequestParam("followingId") Integer followingId) {
        Map<String, Object> map = JwtUtil.parseToken(token);
        Integer followerId = (Integer) map.get("id");
        concernService.deleteConcern(followerId, followingId);
        return Result.success();
    }

}

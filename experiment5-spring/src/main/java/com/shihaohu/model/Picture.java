package com.shihaohu.model;


import lombok.Data;

import java.util.Date;

/**
 * @author Shihaohu
 * @date 2024/6/24 19:13
 */
@Data
public class Picture {
    private Integer id;
    private String name;
    private String url;
    private String userId;
    private String intro;
    private String tags;
    private Date uploadTime;
    private Integer clickNum;
    private String uploadUserName;
}

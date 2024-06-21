package com.shihaohu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Shihaohu
 * @date 2024/6/21 11:58
 */
@Data
public class User {
    private Integer id;
    private String userName;
    private String password;
    private String nickName;
    private String intro;
    private Integer gender;
    private String province;
    private String city;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerTime;
    private Integer type;
    private String email;
    private String mobile;
    private String QQ;
}

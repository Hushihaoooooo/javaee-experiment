package com.shihaohu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Shihaohu
 * @date 2024/6/23 15:18
 */
@Data
public class UserVo {
    private Integer id;
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

package com.shihaohu.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

/**
 * @author Shihaohu
 * @date 2024/6/21 11:58
 */
@Data
public class User {
    @NotNull
    private Integer id;
    @NotNull
    private String userName;
    @JsonIgnore
    @Pattern(regexp = "^\\S{5,16}$")
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
    private String avatar;
    private Integer followerCount;
    private Integer followingCount;

}

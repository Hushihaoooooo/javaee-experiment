package com.shihaohu.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author Shihaohu
 * @date 2024/6/22 20:56
 */
@Data
public class PictureVo {
    private Long id;
    private String name;
    private String fname;
    private Long userId;
    private String intro;
    private String tags;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date uploadTime;
    private Integer clickNum;
    private String creatorName;
}

package com.zfitcode.chat.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author zheng
 * @description TODO
 * @date 2020/12/14
 */
@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String nickname;
    private String password;
    private String avatar;
    private String email;
    private Date createTime;
    private Date updateTime;
    private String gender;
}

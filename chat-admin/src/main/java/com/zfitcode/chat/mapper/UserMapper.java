package com.zfitcode.chat.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.zfitcode.chat.model.entity.User;

/**
 * @author zheng
 * @description TODO
 * @date 2020/12/15
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {
}

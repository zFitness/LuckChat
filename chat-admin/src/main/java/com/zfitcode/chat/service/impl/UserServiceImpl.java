package com.zfitcode.chat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zfitcode.chat.exception.CustomNotFoundException;
import com.zfitcode.chat.exception.UserExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import com.zfitcode.chat.mapper.UserMapper;
import com.zfitcode.chat.model.entity.User;
import com.zfitcode.chat.service.IUserService;

/**
 * @author zheng
 * @description TODO
 * @date 2020/12/15
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    @Override
    public User insertUser(User user) {
        //先查询昵称是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getNickname, user.getNickname());
        List<User> users = userMapper.selectList(wrapper);
        if (users != null && users.size() != 0) {
            throw new RuntimeException("昵称已经被占用");
        }
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        int count = userMapper.insert(user);
        return user;
    }

    @Override
    public User login(String nickname, String password) {
        //先查询用户是否存在
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getNickname, nickname);
        User user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        //判断密码是否正确
        wrapper.eq(User::getPassword, password);
        user = userMapper.selectOne(wrapper);
        if (user == null) {
            throw new RuntimeException("密码错误");
        }
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        return user;
    }
}

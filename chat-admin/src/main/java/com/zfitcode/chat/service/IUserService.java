package com.zfitcode.chat.service;

import com.zfitcode.chat.model.entity.User;

/**
 * @author zheng
 * @description TODO
 * @date 2020/12/14
 */
public interface IUserService {
    public User insertUser(User user);

    public User login(String nickname, String password);

    User getUserById(Integer id);
}

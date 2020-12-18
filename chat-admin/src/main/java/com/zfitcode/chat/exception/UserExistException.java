package com.zfitcode.chat.exception;

/**
 * @author zheng
 * @description 用户不存在
 * @date 2020/12/16
 */
public class UserExistException extends RuntimeException {
    public UserExistException() {
    }

    public UserExistException(String message) {
        super(message);
    }
}

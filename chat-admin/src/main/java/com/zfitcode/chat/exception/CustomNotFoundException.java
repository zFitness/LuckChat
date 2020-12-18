package com.zfitcode.chat.exception;

/**
 * @author zheng
 * @description 用户不存在
 * @date 2020/12/16
 */
public class CustomNotFoundException extends RuntimeException {
    public CustomNotFoundException() {
    }

    public CustomNotFoundException(String message) {
        super(message);
    }
}

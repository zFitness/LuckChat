package com.zfitcode.chat.handler;

import com.zfitcode.chat.exception.UserExistException;
import com.zfitcode.chat.model.dto.result.Result;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author zheng
 * @description TODO
 * @date 2020/12/16
 */
@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler({RuntimeException.class})
    public Result handler(Exception e) {
        return new Result(500, e.getMessage(), null);
    }
}

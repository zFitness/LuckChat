package com.zfitcode.chat.controller;

import com.zfitcode.chat.model.dto.result.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

import com.zfitcode.chat.model.dto.result.Result;
import com.zfitcode.chat.model.entity.User;
import com.zfitcode.chat.service.IUserService;


/**
 * @author zheng
 * @description TODO
 * @date 2020/12/14
 */

@Slf4j
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/greeting")
    public Result greeting() {
        return Result.success("hello, world");
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user) {
        System.out.println(user);
        User newUser = userService.insertUser(user);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userInfo", newUser);
        return Result.success(resultMap);
    }

    @PostMapping("/login")
    public Result login(@RequestParam(value = "nickname", required = true) String nickname,
                        @RequestParam(value = "password", required = true) String password) {
        log.info("nickname: {}, password: {}", nickname, password);
        User user = userService.login(nickname, password);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userInfo", user);
        return Result.success(resultMap);
    }

    @GetMapping("/{id}")
    public Result getUserInfo(@PathVariable Integer id) {
        User user = userService.getUserById(id);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("userInfo", user);
        return Result.success(resultMap);
    }
}

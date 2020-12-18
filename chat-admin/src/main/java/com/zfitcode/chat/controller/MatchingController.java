package com.zfitcode.chat.controller;

import com.zfitcode.chat.model.dto.result.Result;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zheng
 * @description 匹配用户的各种操作
 * @date 2020/12/15
 */
@CrossOrigin
@RestController
@RequestMapping("/match")
public class MatchingController {
    @GetMapping
    public Result match() {

        return Result.success();
    }
}

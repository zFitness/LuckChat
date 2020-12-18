package com.zfitcode.chat.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;
import java.util.HashMap;

/**
 * @author zheng
 * @description TODO
 * @date 2020/11/7
 */
public class JwtUtil {
    /**
     * 过期时间
     */
    private static final long EXPIRE_TIME = 60 * 60 * 1000;

    /**
     * 生成签名
     *
     * @param name
     * @param userId
     * @param password
     * @return
     */
    public static String sign(String name, int userId, String password) {
        //过期时间
        Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
        //使用用户密码作为私钥进行加密
        Algorithm algorithm = Algorithm.HMAC256(password);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        //附带username和userId生成签名
        return JWT.create().withHeader(header)
                .withClaim("userId", userId)
                .withClaim("username", name)
                .withExpiresAt(date)
                .sign(algorithm);

    }

    /**
     * 校验 token
     */
    public static boolean verity(String token, String password) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(password);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}

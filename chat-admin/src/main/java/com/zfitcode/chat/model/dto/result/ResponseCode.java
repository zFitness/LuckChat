package com.zfitcode.chat.model.dto.result;

/**
 * 状态码枚举类
 *
 * @author zfitness
 */
public enum ResponseCode {

    // 公共请求信息
    SUCCESS(200, "请求成功"),
    FAIL(500, "程序错误,杀个程序员祭天"),
    USER_NOT_FOUND(404, "用户不存在"),
    PARAMETER_MISSING(600, "参数错误"),
    UNAUTHORIZED(401, "未授权"),
    PASSWORD_ERROR(500050, "密码错误"),
    // ..一真往后面加

    //用户信息
    //5000100 - 5000200
    USERNAME_REPEAT(5000100, "用户名已存在"),
    PHONE_REPEAT(5000101, "手机号已存在"),
    EMAIL_REPEAT(5000102, "邮箱已存在"),
    //用户-角色
    //5000201 - 5000300
    LINK_NOT_FOUND(404, "该链接不存在"),

    BAD_REQUEST(400, "");

    private Integer code;

    private String message;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }


}

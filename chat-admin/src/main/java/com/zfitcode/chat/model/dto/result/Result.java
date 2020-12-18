package com.zfitcode.chat.model.dto.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;


/**
 * @author zheng
 */
@Data
public class Result implements Serializable {
    /**
     * 状态码
     */
    Integer code;
    /**
     * 返回消息
     */
    String message;
    /**
     * 返回的数据
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    Object data;

    public Result() {
    }

    public Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public void setResultCode(ResponseCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }

    /**
     * 返回成功
     *
     * @return Result
     */
    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResponseCode.SUCCESS);
        return result;
    }


    /**
     * 返回成功的提示和数据
     *
     * @param data 返回的数据
     * @return Result实体类
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResponseCode.SUCCESS);
        result.setData(data);
        return result;
    }


    /**
     * 失败
     *
     * @return Result
     */
    public static Result failure() {
        Result result = new Result();
        result.setResultCode(ResponseCode.FAIL);
        return result;
    }



    /**
     * @param responseCode 失败的状态码
     * @return Result
     */
    public static Result failure(ResponseCode responseCode) {
        Result result = new Result();
        result.setResultCode(responseCode);
        return result;
    }


    /**
     * @param responseCode 状态码
     * @param data         数据
     * @return Result
     */
    public static Result failure(ResponseCode responseCode, Object data) {
        Result result = new Result();
        result.setResultCode(responseCode);
        result.setData(data);
        return result;
    }

}

package com.zfitcode.chat.model;

/**
 * @author zheng
 * @description TODO
 * @date 2020/12/14
 */
public final class MsgType {
    /**
     * 私有构造方法
     */
    private MsgType() {
    }

    public static final String GET_COUNT = "GETCOUNT";
    public static final String MATCH = "MATCH";
    public static final String UN_MATCH = "UN_MATCH";
    public static final String SEND = "SEND";
    public static final String QUIT = "QUIT";
}

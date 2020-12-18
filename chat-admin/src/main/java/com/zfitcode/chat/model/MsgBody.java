package com.zfitcode.chat.model;

import lombok.Data;

/**
 * @author zheng
 * @description TODO
 * @date 2020/12/13
 */
@Data
public class MsgBody {
    /**
     * 消息内容
     */
    private Object content;

    /**
     * 消息类型
     */
    private String type;

    public static MsgBody match(Object content) {
        MsgBody msgBody = new MsgBody();
        msgBody.setType(MsgType.MATCH);
        msgBody.setContent(content);
        return msgBody;
    }

    public static MsgBody size(Object content) {
        MsgBody msgBody = new MsgBody();
        msgBody.setType(MsgType.GET_COUNT);
        msgBody.setContent(content);
        return msgBody;
    }

    public static MsgBody sened(Object content) {
        MsgBody msgBody = new MsgBody();
        msgBody.setType(MsgType.SEND);
        msgBody.setContent(content);
        return msgBody;
    }

    public static MsgBody quit(Object content) {
        MsgBody msgBody = new MsgBody();
        msgBody.setType(MsgType.QUIT);
        msgBody.setContent(content);
        return msgBody;
    }
}

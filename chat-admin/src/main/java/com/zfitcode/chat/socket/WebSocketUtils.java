package com.zfitcode.chat.socket;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.RemoteEndpoint;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * @author zheng
 * @description TODO
 * @date 2020/12/14
 */
@Slf4j
public final class WebSocketUtils {
    /**
     * 存储所有连接 session
     */
    public static final Map<String, Session> LIVING_SESSIONS_CACHE = new ConcurrentHashMap<>();

    /**
     * 存储待匹配
     */
    public static final Map<String, Session> MATCHING_SESSIONS = new ConcurrentHashMap<>();

    /**
     * 群发消息
     *
     * @param message
     */
    public static void sendMessageAll(String message) {
        LIVING_SESSIONS_CACHE.forEach((sessionId, session) -> sendMessage(session, message));
    }

    /**
     * 发送给指定用户消息
     *
     * @param session 用户 session
     * @param message 发送内容
     */
    public static void sendMessage(Session session, String message) {
        log.info("给Session [{}]发送了消息{}", session, message);
        if (session == null) {
            return;
        }
        final RemoteEndpoint.Basic basic = session.getBasicRemote();
        if (basic == null) {
            log.info("basic为{}", basic);
            return;
        }
        try {
            basic.sendText(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
package com.zfitcode.chat.socket;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.Session;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zheng
 * @description TODO
 * @date 2020/12/14
 */
@Slf4j
public final class MatchingSeesion {
    public static final Map<String, Session> LIVING_SESSIONS_CACHE = new ConcurrentHashMap<>();
}

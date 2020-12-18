package com.zfitcode.chat.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zfitcode.chat.model.MsgBody;
import com.zfitcode.chat.model.MsgType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Random;

import static com.zfitcode.chat.socket.MatchingSeesion.LIVING_SESSIONS_CACHE;
import static com.zfitcode.chat.socket.WebSocketUtils.MATCHING_SESSIONS;
import static com.zfitcode.chat.socket.WebSocketUtils.sendMessage;


/**
 * @author zheng
 * @description TODO
 * @date 2020/12/14
 */
@Slf4j
@RestController
@ServerEndpoint("/chat/{userId}")
public class ChatServerEndpoint {
    @OnOpen
    public void openSession(@PathParam("userId") String userId, Session session) {
        Session session1 = LIVING_SESSIONS_CACHE.get(userId);
        //如果用户没有连接,则将用户放在 Map 里
        if (session1 == null) {
            LIVING_SESSIONS_CACHE.put(userId, session);
            String message = "用户[" + userId + "] 上线！";
            log.info(message);
        } else {
            String message = "用户[" + userId + "] 已经在线了！";
            log.info(message);
        }
    }

    /**
     * 接收消息
     *
     * @param userId
     * @param message
     */
    @OnMessage
    public void onMessage(@PathParam("userId") String userId, String message) {
        log.info("用户[{}]发送了消息{}", userId, message);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            JsonNode type = jsonNode.get("type");
            Session session = LIVING_SESSIONS_CACHE.get(userId);
            //获取在线数量
            if (MsgType.GET_COUNT.equals(type.asText())) {
                MsgBody msg = MsgBody.size(LIVING_SESSIONS_CACHE.size());
                sendMessage(session, objectMapper.writeValueAsString(msg));
            }
            //匹配聊天
            else if (MsgType.MATCH.equals(type.asText())) {
                match(userId, session);
            }
            // 解除匹配聊天
            else if (MsgType.UN_MATCH.equals(type.asText())) {
                unMatch(userId, session);
            }
            //退出一对一聊天
            else if (MsgType.QUIT.equals(type.asText())) {
                JsonNode targetId = jsonNode.get("targetId");
                quit(userId, targetId.asText(), session);
            }
            //发送信息给某个用户
            else if (MsgType.SEND.equals(type.asText())) {
                JsonNode targetId = jsonNode.get("targetId");
                String matchId = targetId.asText();
                log.info("发送消息给用户[{}]", matchId);
                Session session1 = LIVING_SESSIONS_CACHE.get(matchId);
                sendMessage(session1, message);
            }
            //得到用户的seesion
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    /**
     * 用户退出后提示另外一个用户
     *
     * @param userId
     * @param targetId
     * @param session
     */
    private void quit(String userId, String targetId, Session session) throws JsonProcessingException {
        log.info("用户[{}]已经离开和用户[{}]聊天!", userId, targetId);
        Session targetSession = LIVING_SESSIONS_CACHE.get(targetId);
        ObjectMapper objectMapper = new ObjectMapper();
        if (targetSession != null) {
            sendMessage(targetSession, objectMapper.writeValueAsString(MsgBody.quit(userId)));
        }
    }

    /**
     * 断开连接
     *
     * @param userId
     * @param session
     */
    @OnClose
    public void onClose(@PathParam("userId") String userId, Session session) {
        log.info("用户[{}]已经断开服务!", userId);
        //当前的Session 移除
        LIVING_SESSIONS_CACHE.remove(userId);

        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误
     *
     * @param session
     * @param throwable
     */
    @OnError
    public void onError(Session session, Throwable throwable) {
        try {
            session.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throwable.printStackTrace();
    }

    /**
     * 随机匹配
     *
     * @param userId
     * @param session
     * @throws JsonProcessingException
     */
    public synchronized void match(String userId, Session session) throws JsonProcessingException {
        log.info("用户[{}]进入匹配", userId);
        ObjectMapper objectMapper = new ObjectMapper();
        //本用户已经在匹配队列中了
        if (MATCHING_SESSIONS.get(userId) != null) {
            MATCHING_SESSIONS.remove(userId);
        }
        // 匹配集合没有正在匹配的用户则放入
        if (MATCHING_SESSIONS.size() == 0) {
            MATCHING_SESSIONS.put(userId, session);
        } else {
            String[] userIds = MATCHING_SESSIONS.keySet().toArray(new String[0]);
            Random random = new Random();
            //匹配到的userId
            String matchedUserId = userIds[random.nextInt(userIds.length)];
            Session matchedSession = LIVING_SESSIONS_CACHE.get(matchedUserId);


            //匹配成功，发送消息通知两者
            MsgBody msg = MsgBody.match(matchedUserId);
            sendMessage(session, objectMapper.writeValueAsString(msg));

            MsgBody msg1 = MsgBody.match(userId);
            sendMessage(matchedSession, objectMapper.writeValueAsString(msg1));

            //将匹配成功的人移除匹配队列
            MATCHING_SESSIONS.remove(userId);
            MATCHING_SESSIONS.remove(matchedUserId);
        }
    }

    /**
     * 退出匹配
     *
     * @param userId
     * @param session
     * @throws JsonProcessingException
     */
    public synchronized void unMatch(String userId, Session session) throws JsonProcessingException {
        log.info("用户[{}]退出匹配", userId);
        //本用户已经在匹配队列中了
        if (MATCHING_SESSIONS.get(userId) != null) {
            MATCHING_SESSIONS.remove(userId);
        }
    }

}

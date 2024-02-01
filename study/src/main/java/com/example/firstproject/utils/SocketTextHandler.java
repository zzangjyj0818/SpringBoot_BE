package com.example.firstproject.utils;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class SocketTextHandler extends TextWebSocketHandler {
    private final Set<WebSocketSession> sessions = new HashSet<>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        sessions.add(session);

        System.out.println("새 클라이언트와 연결되었습니다.");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws IOException {
        System.out.println(message.getPayload());

        for (WebSocketSession connectedSession : sessions) {
            connectedSession.sendMessage(message);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session,
                                      CloseStatus status) {
        sessions.remove(session);

        System.out.println("특정 클라이언트와의 연결이 해제되었습니다.");
    }
}

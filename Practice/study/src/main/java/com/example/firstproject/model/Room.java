package com.example.firstproject.model;

import com.example.firstproject.utils.RoomIdGenerator;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

public class Room {
    private Long id;

    private String name;

    private final Set<WebSocketSession> sessions = new HashSet<>();

    public static Room create(String name) {
        Room room = new Room();
        room.id = RoomIdGenerator.createId();
        room.name = name;
        return room;
    }

    public Long id() {
        return id;
    }

    public Set<WebSocketSession> sessions() {
        return sessions;
    }
}
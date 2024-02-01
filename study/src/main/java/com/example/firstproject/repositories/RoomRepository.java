package com.example.firstproject.repositories;

import com.example.firstproject.model.Room;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class RoomRepository {
    private final Map<Long, Room> rooms;

    public RoomRepository() {
        rooms = Stream.of(
                Room.create("1번 채팅방"),
                Room.create("2번 채팅방"),
                Room.create("3번 채팅방")
        ).collect(Collectors.toMap(
                Room::id,
                room -> room
        ));
    }

    public Room room(Long id) {
        return rooms.get(id);
    }
}

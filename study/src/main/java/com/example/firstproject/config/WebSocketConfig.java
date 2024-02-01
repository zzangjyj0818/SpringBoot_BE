package com.example.firstproject.config;
// config/WebSocketConfig.java

import com.example.firstproject.utils.SocketTextHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(socketTextHandler(), "/chat/rooms/{roomId}")
                .setAllowedOriginPatterns("http://localhost:9991");
    }

    @Bean
    public WebSocketHandler socketTextHandler() {
        return new SocketTextHandler();
    }
}
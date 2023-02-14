package com.websocket.RadioChanel.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.web.socket.WebSocketSession;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SessionWrapper {
    private WebSocketSession session;
    private String userId;
}

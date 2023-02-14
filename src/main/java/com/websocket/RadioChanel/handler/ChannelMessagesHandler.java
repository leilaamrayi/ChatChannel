package com.websocket.RadioChanel.handler;

import com.websocket.RadioChanel.model.Channel;
import com.websocket.RadioChanel.repository.ChannelRepository;
import com.websocket.RadioChanel.web.SessionWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ChannelMessagesHandler extends TextWebSocketHandler {

    private int mode = 1; // 1 = main-channel , 2 = chat-channel
    private ChannelRepository channelRepository;
    private Map<String, SessionWrapper> sessions = new HashMap<>();
    private List<String> allChannelMessages = new ArrayList<>();
    private int sessionCount = 0;

    private Channel channel;

    public void setChannelRepository(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public void chatMode(){
        mode = 2;
    }

    public void channelMode(){
        mode = 1;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String userId = getUserId(session);
        System.out.println(userId+" connected");
        sessions.put(session.getId(), new SessionWrapper(session, userId));
        notifyNewUserConnected(userId);
        sendChannelInfoToTheChat(session);
        sendChannelListToUser(session);
        sendMessageHistoryToUser(session);
    }

    private void sendChannelInfoToTheChat(WebSocketSession session) throws IOException {
        String messageToSend = "---- Welcome to "+channel.getTitle()+" channel ----";
        session.sendMessage(new TextMessage(messageToSend));
    }

    private void sendChannelListToUser(WebSocketSession session) throws IOException {
        if (mode == 2){
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Available channels => ");
        var channels = channelRepository.findAll();
        var channelsAsStringList = channels.stream().map(Channel::toString).toList();
        var channelsAsString = String.join(",",channelsAsStringList);
        sb.append(channelsAsString);
        String messageToSend = sb.toString();
        System.out.println("Sending channel list to user:");
        System.out.println(messageToSend);
        session.sendMessage(new TextMessage(messageToSend));
    }

    private void sendMessageHistoryToUser(WebSocketSession session) {
        allChannelMessages.forEach(message -> {
            try {
                session.sendMessage(new TextMessage(message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void notifyNewUserConnected(String userId) throws IOException {
        String messageToSend = userId+" joined";
        for (var sessionEntry : sessions.entrySet()) {
            if (!sessionEntry.getValue().getUserId().equals(userId)) {
                sessionEntry.getValue().getSession().sendMessage(new TextMessage(messageToSend));
            }
        }
    }

    private String getUserId(WebSocketSession session){
        var userIdHeaderList = session.getHandshakeHeaders().get("userId");
        if (userIdHeaderList == null || userIdHeaderList.isEmpty()){
            return "user-"+(++sessionCount);
        } else {
            return userIdHeaderList.get(0);
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        var foundSession = sessions.get(session.getId());
        sessions.remove(session.getId());
        System.out.println(foundSession.getUserId()+" was removed");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        var foundSession = sessions.get(session.getId());
        String messageToSend = String.format("%s: %s",foundSession.getUserId(), message.getPayload());
        allChannelMessages.add(messageToSend);
        for (var sessionEntry : sessions.entrySet()) {
            sessionEntry.getValue().getSession().sendMessage(new TextMessage(messageToSend));
        }
    }

    public void channelCreated(Channel channel) {
        if (mode == 2){
            return;
        }
        String messageToSend = "New channel created: "+channel;
        for (var sessionEntry : sessions.entrySet()) {
            try {
                sessionEntry.getValue().getSession().sendMessage(new TextMessage(messageToSend));
            } catch (IOException e) {
                System.out.println("Could not send notification to channel.");
                throw new RuntimeException(e);
            }
        }
    }
}

package com.websocket.RadioChanel.config;

import com.websocket.RadioChanel.handler.ChannelMessagesHandler;
import com.websocket.RadioChanel.model.Channel;
import com.websocket.RadioChanel.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebsocketConfig implements WebSocketConfigurer {

    private final static String SOCKET_PREFIX = "/sub";
    private final static String CHANNELS = "/channels";
    private final static String CHAT = "/chat";


    @Autowired
    private ChannelRepository channelRepository;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getChannelMessagesHandlerForChannel(), SOCKET_PREFIX+CHANNELS).setAllowedOrigins("*");
        registerChatChannelHandlers(registry);
    }

    private void registerChatChannelHandlers(WebSocketHandlerRegistry registry) {
        var channels = channelRepository.findAll();
        channels.forEach(channel -> {
            var handler = getChannelMessagesHandlerForChat();
            handler.setChannel(channel);
            String path = SOCKET_PREFIX+CHAT+"/"+channel.getId();
            registry.addHandler(handler, path).setAllowedOrigins("*");
        });
    }

    @Bean
    public ChannelMessagesHandler getChannelMessagesHandlerForChannel() {
        var handler = new ChannelMessagesHandler();
        handler.setChannelRepository(channelRepository);
        handler.channelMode();
        handler.setChannel(new Channel().setTitle("MAIN"));
        return handler;
    }

    public ChannelMessagesHandler getChannelMessagesHandlerForChat() {
        var handler = new ChannelMessagesHandler();
        handler.setChannelRepository(channelRepository);
        handler.chatMode();
        return handler;
    }
}

       /* @Override
        public void configureMessageBroker(MessageBrokerRegistry config) {
            config.enableSimpleBroker("/topic");
            config.setAppcjlicationDestinationPrefixes("/app");
        }

        @Override
        public void registerStompEndpoints(StompEndpointRegistry registry) {
            registry.addEndpoint("/radioChanel");
        }
    }*/



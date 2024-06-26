package com.chat.socketIO.config;
import com.corundumstudio.socketio.SocketIOServer;
import io.netty.handler.codec.http.HttpObjectAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SocketIOConfig {

    @Value("${socket-server.host}")
    private String host;

    @Value("${socket-server.port}")
    private Integer port;

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
        config.setHostname(host);
        config.setPort(port);
        // buffer size
        config.setMaxFramePayloadLength(10 * 1024 * 1024);
        config.setMaxHttpContentLength(10 * 1024 * 1024);
//        new HttpObjectAggregator(10 * 1024 * 1024);

        return new SocketIOServer(config);
    }

}

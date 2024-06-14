package com.chat.socketIO.socket;

import com.chat.socketIO.service.SocketService;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import com.chat.socketIO.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

@Slf4j
@Component
@CrossOrigin
public class SocketModule {


    private final SocketIOServer server;
    private final SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
    }
    public void startSocketModule(){
        log.info("Starting socket module ");
        server.addConnectListener(onConnected());
        server.addDisconnectListener(onDisconnected());
        server.addEventListener("send_message", Message.class, onChatReceived());
        log.info("Started socket module ");
    }


    private DataListener<Message> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            log.info("data received in dataListener : {} -----> by client : {} in room : {}" ,
                    data.getText() , senderClient.getSessionId() , data.getRoom()
            );
            socketService.sendMessage(data.getRoom(), "get_message", senderClient, data);
            ackSender.sendAckData("message sent in room : " + data.getRoom());
        };
    }


    private ConnectListener onConnected() {
        return (client) -> {
            String room = client.getHandshakeData().getSingleUrlParam("room");
            client.joinRoom(room);
            log.info("Socket ID[{}]  Connected to socket-server in room : {} ", client.getSessionId().toString(), room);
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            client.disconnect();
            log.info("Client[{}] - Disconnected from socket", client.getSessionId().toString());
        };
    }

}

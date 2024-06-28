package com.chat.socketIO.controller;

import com.chat.socketIO.socket.SocketUtils;
import com.corundumstudio.socketio.SocketIOServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private final SocketIOServer socketIOServer;
    private final SocketUtils socketUtils;
    @Autowired
    public TestController(SocketIOServer server , SocketUtils socketUtils){
        this.socketIOServer = server;
        this.socketUtils = socketUtils;
    }
    @GetMapping("/ping")
    public String pongController(){
        return "pong !!!!!!!!!!!!!!!";
    }

    @GetMapping("/start")
    public String startServerController(){
        socketUtils.startSocketModule();
        socketIOServer.start();
        return "Socket server started !";
    }
    @GetMapping("/stop")
    public String stopServerController(){
        socketIOServer.stop();
        return "Socket server stopped !";
    }
}

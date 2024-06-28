package com.chat.socketIO.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private MessageType type;
    private String text;
    private String room;
    private String sender;
    private FileData mediaFile;
    public Message() {
    }

    public Message(MessageType type, String message) {
        this.type = type;
        this.text = message;
    }
    public Message(String message){
        this.text = message;
    }
    public Message(String message ,String room , String sender){
        this.text = message;
        this.room = room;
        this.sender = sender;
    }

    public Message(String message , String room , String sender , FileData media){
        this.text = message;
        this.room = room;
        this.sender = sender;
        this.mediaFile = media;
    }
    public String toString(){
        return "Message[" +
                "text : " +this.text+" ," +
                "sender : " +this.sender+" ," +
                "room : " +this.room+" ," +
                "mediaFile : " +this.mediaFile +
                "]";
    }
}


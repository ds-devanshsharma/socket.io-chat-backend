package com.chat.socketIO.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
public class FileData implements Serializable {
    private String fileName;
    private String fileType;
    private byte[] data;

    public FileData(String fileName, String fileType, byte[] data) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.data = data;
    }

    public FileData() {
    }
}

package com.kevinyin.lsocket;

import java.io.Serializable;

/**
 * Created by Kevin_Yin on 2017/6/30.
 */
public class TcpIpMessage implements Serializable{
    private String uuid;
    private Integer type;
    private String message;

    public TcpIpMessage(String uuid, Integer type, String message) {
        this.uuid = uuid;
        this.type = type;
        this.message = message;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "TcpIpMessage{" +
                "uuid='" + uuid + '\'' +
                ", type=" + type +
                ", message='" + message + '\'' +
                '}';
    }
}


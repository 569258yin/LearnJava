package com.kevinyin.lnio;

import java.io.Serializable;

/**
 * Created by Kevin_Yin on 2017/6/29.
 */
public class NioMessage implements Serializable{

    private int type;
    private String message;

    public NioMessage() {
    }

    public NioMessage(int type, String message) {
        this.type = type;
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

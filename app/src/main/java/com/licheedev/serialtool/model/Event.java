package com.licheedev.serialtool.model;

public class Event{
    private int code;
    private String msg;

    public Event(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
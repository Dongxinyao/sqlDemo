package com.example.sqldemo.bean;

public class Result<T> {
    public long code;
    public String message;
    public String db_message;
    public T data;

    public Result(long code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(long code, String message, String db_message, T data) {
        this.code = code;
        this.message = message;
        this.db_message = db_message;
        this.data = data;
    }

    public Result(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }
}

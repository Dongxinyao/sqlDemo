package com.example.sqldemo.exception;

import java.io.IOException;

public class ParamsException extends Exception {
    public ParamsException() {
        super("the backupsPath set failed");
    }

    public ParamsException(String listName) {
        super("the " + listName + " is empty");
    }

    public ParamsException(IOException e) {
        super(e.getMessage(), e.getCause());
    }
}

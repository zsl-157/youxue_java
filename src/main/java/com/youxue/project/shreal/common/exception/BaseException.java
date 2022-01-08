package com.youxue.project.shreal.common.exception;

public class BaseException extends Exception {
    private int code;
    private String msg;

    public BaseException(int code,String msg){
        super(msg);
        this.msg = msg;
        this.code = code;
    }
}

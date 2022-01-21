package com.youxue.project.shreal.entity;


public class Result<T> {
    public Integer code;
    public String msg;
    public T data;

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setCm(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public void setCmd(Integer code,String msg,T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

}

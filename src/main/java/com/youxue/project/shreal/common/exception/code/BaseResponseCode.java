package com.youxue.project.shreal.common.exception.code;

import com.fasterxml.jackson.databind.ser.Serializers;

public enum BaseResponseCode implements ResponseCodeInterface {
    SUCCESS(0,"操作成功"),
    SYSTEM_BUSY(500001,"系统繁忙，请稍后再试"),
    OPERATION_ERROR(500002,"操作失败"),
    TOKEN_ERROR(401001,"凭证过期，请重新登录"),
    DATA_ERROR(401003,"传入数据异常"),
    NOT_ACCOUNT(401004,"用户不存在，请先注册"),
    USER_LOCK(401005,"用户已锁定，请联系管理员"),
    PASSWORD_ERROR(401006,"密码错误"),
    METHODARGUMENTNOTVALIDEEXCEPTION(410007,"方法参数不正确");


    private final int code;
    private final String message;

    BaseResponseCode(int code,String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode(){
        return code;
    }

    @Override
    public String getMessage(){
        return message;
    }

}



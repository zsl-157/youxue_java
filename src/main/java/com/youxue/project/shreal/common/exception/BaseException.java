package com.youxue.project.shreal.common.exception;

import com.youxue.project.shreal.common.exception.code.BaseResponseCode;
import com.youxue.project.shreal.common.exception.code.ResponseCodeInterface;

public class BaseException extends RuntimeException {
    private final int code;
    private final String msg;

    public BaseException(int code,String msg){
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BaseException(String msg){
        super(msg);
        this.code = BaseResponseCode.OPERATION_ERROR.getCode();
        this.msg = msg;
    }
    public BaseException(ResponseCodeInterface codeInterface){
        this(codeInterface.getCode(),codeInterface.getMessage());
    }

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }
}

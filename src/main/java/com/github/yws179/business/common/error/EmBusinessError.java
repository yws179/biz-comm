package com.github.yws179.business.common.error;

/**
 * @author yws
 * @date 2019/04/24
 */
public enum EmBusinessError implements CommonError {

    PARAMTER_VALIDATION_ERROR(10000, ""),

    USER_NOT_EXIST(10001, "user not exist")

    ;

    private int errCode;

    private String errMsg;

    EmBusinessError(int errCode, String errMsg) {
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    @Override
    public int getErrorCode() {
        return this.errCode;
    }

    @Override
    public String getErrMsg() {
        return this.errMsg;
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.errMsg = errMsg;
        return this;
    }
}

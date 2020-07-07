package com.github.yws179.business.common.error;

/**
 * @author yws
 * @date 2019/04/24
 */
public enum EmBusinessError implements CommonError {
    // --------- 请求相关 --------
    /**
     * 参数校验失败
     */
    PARAM_VALIDATION_ERROR(10000, "param validation error"),
    /**
     * 请求地址不存在
     */
    RESOURCE_NOT_FOUND(10001, "resource not found"),

    // --------- 用户相关 --------
    /**
     * 用户不存在
     */
    USER_NOT_EXIST(11001, "user not exist")

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

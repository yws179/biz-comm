package com.github.yws179.business.common.error;

import org.springframework.http.HttpStatus;

/**
 * 包装器业务异常类实现
 * @author yws
 * @date 2019/04/24
 */
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    public static BusinessException of(CommonError commonError) {
        return new BusinessException(commonError);
    }

    public static BusinessException of(CommonError commonError, Throwable cause) {
        return new BusinessException(commonError, cause);
    }

    public static BusinessException of(CommonError commonError, String errMsg) {
        return new BusinessException(commonError, errMsg);
    }

    public static BusinessException of(CommonError commonError, String errMsg, Throwable cause) {
        return new BusinessException(commonError, errMsg, cause);
    }

    public BusinessException(CommonError commonError) {
        super();
        this.commonError = commonError;
    }

    public BusinessException(CommonError commonError, Throwable cause) {
        super(cause);
        this.commonError = commonError;
    }

    public BusinessException(CommonError commonError, String errMsg) {
        super();
        this.commonError = commonError.setErrMsg(errMsg);
    }

    public BusinessException(CommonError commonError, String errMsg, Throwable cause) {
        super(cause);
        this.commonError = commonError.setErrMsg(errMsg);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return this.commonError.getHttpStatus();
    }

    @Override
    public int getErrorCode() {
        return this.commonError.getErrorCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        return this.commonError.setErrMsg(errMsg);
    }
}

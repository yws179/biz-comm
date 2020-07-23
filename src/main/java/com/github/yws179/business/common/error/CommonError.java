package com.github.yws179.business.common.error;

import org.springframework.http.HttpStatus;

/**
 * @author yws
 * @date 2019/04/24
 */
public interface CommonError {

    HttpStatus getHttpStatus();

    int getErrorCode();

    String getErrMsg();

    CommonError setErrMsg(String errMsg);

}

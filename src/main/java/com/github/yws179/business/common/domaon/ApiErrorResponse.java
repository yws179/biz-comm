package com.github.yws179.business.common.domaon;

import com.github.yws179.business.common.error.CommonError;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yws
 * @date 2020/07/08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiErrorResponse {
    /**
     * 错误码
     */
    private Integer code;
    /**
     * 错误信息
     */
    private String msg;

    public static ApiErrorResponse of(CommonError error) {
        return new ApiErrorResponse(error.getErrorCode(), error.getErrMsg());
    }

    public static ApiErrorResponse of(Integer code, String msg) {
        return new ApiErrorResponse(code, msg);
    }

}

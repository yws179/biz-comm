package com.github.yws179.business.common.error;

import com.github.yws179.business.common.domaon.ApiErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

/**
 * 抽象全局异常处理器
 * 使用时需要创建一个 GlobalExceptionHandler 继承该类，然后添加 @ControllerAdvice 注解
 * @author yws
 * @date 2020/07/07
 */
@Slf4j
@ControllerAdvice
public abstract class AbstractExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity handleBusinessException(BusinessException e) {
        HttpStatus status = e.getHttpStatus();
        if (status.is4xxClientError()) {
            log.warn("用户异常操作[msg: {}]", e.getErrMsg());
        } else if (status.is5xxServerError()) {
            log.error("异常", e);
        }
        return ResponseEntity.status(status).body(ApiErrorResponse.of(e));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        return handleBusinessException(BusinessException.of(EmBusinessError.UNKNOWN_ERROR, e));
    }

    /**
     * 处理参数校验异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));
        return handleBusinessException(BusinessException.of(EmBusinessError.PARAM_VALIDATION_ERROR, msg));
    }

}

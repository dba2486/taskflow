package com.taskflow.global.exception;

import com.taskflow.global.response.ApiResponse;
import com.taskflow.global.response.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * CustomException 처리
     */
    @ExceptionHandler(CustomException.class)
    public ApiResponse<?> handleCustomException(CustomException ex) {
        ErrorResponse error = ErrorResponse.from(ex.getErrorCode());
        return ApiResponse.error(error);
    }

    /**
     * 예상치 못한 서버 오류 처리
     */
    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception ex) {
        ErrorResponse error = ErrorResponse.from(ErrorCode.INTERNAL_SERVER_ERROR);
        ex.printStackTrace();   // 개발 단계에서 에러 로그 확인용
        return ApiResponse.error(error);
    }
}

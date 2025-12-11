package com.taskflow.global.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.taskflow.global.response.ApiResponse;
import com.taskflow.global.response.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * ValidException 처리
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<?> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        ErrorResponse error = new ErrorResponse(ErrorCode.INVALID_REQUEST.name(), ex.getBindingResult().getFieldError().getDefaultMessage());
        return ApiResponse.error(error);
    }

    /**
     * InvalidJsonException 처리
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiResponse<?>> handleInvalidJsonException(InvalidFormatException ex) {

        String fieldName = ex.getPath().isEmpty() ? "unknown" : ex.getPath().get(0).getFieldName();

        //  날짜 포맷 오류의 경우
        if (ex.getTargetType() != null && ex.getTargetType().equals(java.time.LocalDateTime.class)) {

            String message = String.format("%s 필드는 'yy-MM-dd HH:mm:ss' 형식이어야 합니다.", fieldName);

            ErrorResponse error = new ErrorResponse(ErrorCode.INVALID_REQUEST.name(), message);

            return ResponseEntity.badRequest().body(ApiResponse.error(error));
        }

        // ENUM 변환 실패 오류
        if (ex.getTargetType() != null && ex.getTargetType().isEnum()) {
            ErrorResponse error = ErrorResponse.from(ErrorCode.INVALID_ENUM_VALUE);
            return ResponseEntity.badRequest().body(ApiResponse.error(error));
        }

        // 그 외 JSON 오류
        ErrorResponse error = ErrorResponse.from(ErrorCode.INVALID_REQUEST);
        return ResponseEntity.badRequest().body(ApiResponse.error(error));
    }

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

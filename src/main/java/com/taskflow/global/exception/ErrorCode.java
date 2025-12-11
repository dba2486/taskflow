package com.taskflow.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // Common
    INVALID_REQUEST("잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR("서버 내부 오류가 발생했습니다."),

    // User
    USER_NOT_FOUND("사용자를 찾을 수 없습니다."),
    EMAIL_ALREADY_EXISTS("이미 사용 중인 이메일입니다."),

    // Category
    CATEGORY_NOT_FOUND("카테고리를 찾을 수 없습니다."),

    // Task
    TASK_NOT_FOUND("업무를 찾을 수 없습니다."),
    INVALID_ENUM_VALUE("올바르지 않은 ENUM 값입니다. 'TODO', 'IN_PROGRESS', 'DONE' 중에 입력해주세요.");

    private final String message;
}

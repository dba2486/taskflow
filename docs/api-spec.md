# TaskFlow API Specification

본 문서는 TaskFlow 백엔드 API의 요청/응답 규격을 정리한 기술 명세서입니다.  
`api-endpoints.md` 가 전체 엔드포인트 구조를 요약한다면,  
본 문서는 각 API의 **세부 스펙(Request / Response / Error)** 을 다룹니다.

---

# 공통 규칙 (Common Spec)

## ✔ 공통 Response 형식

### 성공 응답

```json
{
  "success": true,
  "data": {},
  "error": null
}
```

### 실패 응답

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "USER_NOT_FOUND",
    "message": "사용자를 찾을 수 없습니다."
  }
}
```

---

# ErrorCode 목록

| code                  | Message           |
|-----------------------|-------------------|
| INVALID_REQUEST       | 잘못된 요청입니다.        |
| INTERNAL_SERVER_ERROR | 서버 내부 오류가 발생했습니다. |
| USER_NOT_FOUND        | 사용자를 찾을 수 없습니다.   |
| EMAIL_ALREADY_EXISTS  | 이미 사용 중인 이메일입니다.  |
| CATEGORY_NOT_FOUND    | 카테고리를 찾을 수 없습니다.  |
| TASK_NOT_FOUND        | 업무를 찾을 수 없습니다.    |

---

# User API

## 1. 사용자 등록(Register) — POST `/api/v1/users`

### RequestBody

```json
{
  "name": "테스트사용자",
  "email": "test@example.com",
  "password": "1234"
}
```

### Validations

| Field    | Rule                 | Message                                |
|----------|----------------------|----------------------------------------|
| name     | NotBlank             | 이름은 필수입니다.                             |
| email    | NotBlank<br/> Email  | 이메일은 필수 입니다. <br/>올바른 이메일 형식이 아닙니다.    |
| password | NotBlank<br/> 4~100자 | 비밀번호는 필수 입니다.<br/> 비밀번호는 4~100자여야 합니다. |

### Response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "테스트사용자",
    "email": "test@example.com"
  },
  "error": null
}
```

### Validation 실패 시

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "INVALID_REQUEST",
    "message": "비밀번호는 필수입니다."
  }
}
```

### 이메일 중복 오류

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "EMAIL_ALREADY_EXISTS",
    "message": "이미 사용 중인 이메일입니다."
  }
}
```

---

## 2. 사용자 단건 조회 — GET `/api/v1/users/{userId}`

### Response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "테스트사용자",
    "email": "test@example.com"
  },
  "error": null
}
```

### 존재하지 않는 ID 오류

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "USER_NOT_FOUND",
    "message": "사용자를 찾을 수 없습니다."
  }
}
```

---

## 3. 사용자 정보 수정 — PATCH `/api/v1/users/{id}`

### Request Body

```json
{
  "name": "수정된사용자",
  "password": "1357926"
}
```

### Response

```json
{
  "id": 1,
  "name": "수정된사용자",
  "email": "test@example.com"
}
```

---

## 4. 사용자 삭제 — DELETE `/api/v1/users/{id}`

### Response

```json
{
  "success": true,
  "data": null,
  "error": null
}
```

---

# Category API

## 1. 카테고리 생성 — POST `/api/v1/users/{userId}/categories`

### Request

```json
{
  "name": "카테고리명"
}
```

### Validations

| Field | Rule     | Message         |
|-------|----------|-----------------|
| name  | NotBlank | 카테고리 이름은 필수입니다. |

### Response

```json
{
  "success": true,
  "data": {
    "id": 2,
    "name": "카테고리명"
  },
  "error": null
}
```

### Validation 실패 시

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "INVALID_REQUEST",
    "message": "카테고리 이름은 필수입니다."
  }
}
```

### 존재하지 않는 사용자 ID 오류

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "USER_NOT_FOUND",
    "message": "사용자를 찾을 수 없습니다."
  }
}
```

---

## 2. 특정 사용자의 카테고리 목록 조회 — GET `/api/v1/users/{userId}/categories`

### Response

```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "name": "테스트 카테고리"
    },
    {
      "id": 2,
      "name": "카테고리명"
    }
  ],
  "error": null
}
```

---

## 3. 카테고리 단건 조회 — GET `/api/v1/categories/{categoryId}`

### Response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "테스트 카테고리"
  },
  "error": null
}
```

### 존재하지 않는 카테고리 ID 오류

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "CATEGORY_NOT_FOUND",
    "message": "카테고리를 찾을 수 없습니다."
  }
}
```

---

## 4. 카테고리 이름 수정 — PATCH `/api/v1/categories/{categoryId}`

### Request

```json
{
  "name": "새로운 카테고리"
}
```

### Response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "name": "새로운 카테고리"
  },
  "error": null
}
```

---

## 5. 카테고리 삭제 — DELETE `/api/v1/categories/{categoryId}`

### Response

```json
{
  "success": true,
  "data": null,
  "error": null
}
```

---

# Task API

## 1. 업무 생성 — POST `/api/v1/users/{userId}/tasks`

### RequestBody

```json
{
  "title": "테스트업무",
  "description": "업무에 대한 설명",
  "dueDate": "2025-12-10 15:00:00",
  "priority": 1,
  "status": "TODO",
  "categoryId": 1
}
```

### Validations

| Field      | Rule                      | Message                                                      |
|------------|---------------------------|--------------------------------------------------------------|
| title      | NotBlank                  | 업무 제목은 필수입니다.                                                |
| dueDate    | NotNull<br/>LocalDateTime | 마감기한은 필수입니다.<br/> dueDate 필드는 'yy-MM-dd HH:mm:ss' 형식이어야 합니다. |
| priority   | NotNull                   | 우선순위는 필수입니다.                                                 |
| status     | NotBlank                  | 업무 상태는 필수입니다.                                                |
| categoryId | NotNull                   | 카테고리 ID는 필수입니다.                                              |    

### Response

```json
{
  "success": true,
  "data": {
    "id": 9,
    "title": "테스트업무",
    "description": "업무에 대한 설명",
    "priority": "1",
    "status": "TODO",
    "dueDate": "2025-12-10 15:00:00",
    "categoryId": 1
  },
  "error": null
}
```

### Validation 실패 시

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "INVALID_REQUEST",
    "message": "업무 제목은 필수입니다."
  }
}
```

### LocalDateTime Formate 오류 시

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "INVALID_REQUEST",
    "message": "dueDate 필드는 'yy-MM-dd HH:mm:ss' 형식이어야 합니다."
  }
}
```

---

## 2. 특정 사용자의 모든 업무 조회 — GET `/api/v1/users/{userId}/tasks`

### Response

```json
{
  "success": true,
  "data": [
    {
      "id": 1,
      "title": "과학공부",
      "description": "가계도 공부",
      "priority": "2",
      "status": "TODO",
      "dueDate": "2025-12-11 13:22:19",
      "categoryId": 1
    },
    {
      "id": 9,
      "title": "테스트업무",
      "description": "업무에 대한 설명",
      "priority": "1",
      "status": "TODO",
      "dueDate": "2025-12-16 15:00:00",
      "categoryId": 1
    }
  ],
  "error": null
}
```

---

## 3. 업무 단건 조회 — GET `/api/v1/tasks/{taskId}`

### Response

```json
{
  "id": 9,
  "title": "테스트업무",
  "description": "업무에 대한 설명",
  "priority": "1",
  "status": "TODO",
  "dueDate": "2025-12-16 15:00:00",
  "categoryId": 1
}
```

### 존재하지 않는 업무 ID 오류

```json
{
  "success": false,
  "data": null,
  "error": {
    "code": "TASK_NOT_FOUND",
    "message": "업무를 찾을 수 없습니다."
  }
}
```

---

## 4. 업무 수정 — PATCH `/api/v1/tasks/{taskId}`

### RequestBody

```json
{
  "title": "수정 테스트업무",
  "description": "수정된 업무에 대한 설명",
  "priority": 3,
  "status": "IN_PROGRESS",
  "dueDate": "2025-12-15 12:00:00"
}
```

### Validations 업무 생성과 동일

### Response

```json
{
  "success": true,
  "data": {
    "id": 1,
    "title": "수정 테스트업무",
    "description": "수정된 업무에 대한 설명",
    "priority": "3",
    "status": "IN_PROGRESS",
    "dueDate": "2025-12-15 12:00:00",
    "categoryId": 1
  },
  "error": null
}
```

---

## 5. 업무 삭제(Soft Delete) — DELETE `/api/v1/tasks/{taskId}`

### Response

```json
{
  "success": true,
  "data": null,
  "error": null
}
```

---

# Version History

| Version | Date       | Description                          |
|---------|------------|--------------------------------------|
| v0.2.0  | 2025-12-11 | User/Category/Task CRUD 상세 API 명세 추가 |
| v0.1.0  | 2025-12-10 | `api-spec.md` 초안 작성                  |


# API Endpoints (v0.1.0)

TaskFlow 프로젝트의 v0.1.0 기준 API 엔드포인트 목록입니다.  
이 문서는 **엔드포인트 구조 요약** 용도로 작성되었으며,  
자세한 Request/Response 스펙은 `api-spec.md`에서 다룹니다.

---

## Auth (인증)

| Method | Endpoint       | Description  |
|--------|----------------|--------------|
| POST   | `/auth/signup` | 회원가입         |
| POST   | `/auth/login`  | 로그인 (JWT 발급) |

---

## Users (사용자)

| Method | Endpoint      | Description   |
|--------|---------------|---------------|
| POST   | `/users`      | 회원 가입(사용자 생성) |
| GET    | `/users/{id}` | 사용자 단건 조회     |
| PATCH  | `/users/{id}` | 사용자 정보 수정     |
| DELETE | `/users/{id}` | 사용자 삭제        |

---

### 1.1 POST `/users` — 회원가입(사용자 생성)

#### Request Body

```json
{
  "name": "테스트사용자",
  "email": "test@example.com",
  "password": "1234"
}
```

#### Response

```json
{
  "id": 1,
  "name": "테스트사용자",
  "email": "test@example.com"
}
```

---

### 1.2 GET `/users/{id}` — 사용자 단건 조회

#### Path Parameter

- `id`: 조회할 사용자 ID

#### Response

```json
{
  "id": 1,
  "name": "테스트사용자",
  "email": "test@example.com"
}
```

---

### 1.3 PATCH `/users/{id}` — 사용자 수정

#### Path Parameter

- `id`: 수정할 사용자 ID

#### Request Body

```json
{
  "name": "수정된사용자",
  "password": "1357926"
}
```

#### Response

```json
{
  "id": 1,
  "name": "수정된사용자",
  "email": "test@example.com"
}
```

---

### 1.4 DELETE `/users/{id}` — 사용자 삭제

#### Path Parameter

- `id`: 삭제할 사용자 ID

#### Response

```json
{
  "success": true,
  "data": null,
  "error": null
}
```

---

## Categories (카테고리)

| Method | Endpoint                     | Description        |
|--------|------------------------------|--------------------|
| POST   | `/users/{userId}/categories` | 카테고리 생성            |
| GET    | `/users/{userId}/categories` | 특정 사용자의 카테고리 목록 조회 |
| GET    | `/categories/{categoryId}`   | 카테고리 단건 조회         |
| PATCH  | `/categories/{categoryId}`   | 카테고리 이름 수정         |
| DELETE | `/categories/{categoryId}`   | 카테고리 삭제            |

---

### 2.1 POST `/users/{userId}/categories` — 카테고리 생성

#### Path Parameter

- `userId`: 카테고리를 소유한 사용자 ID

#### Request Body#

```json
{
  "name": "테스트 카테고리"
}
```

#### Response

```json
{
  "id": 2,
  "name": "테스트 카테고리"
}
```

---

### 2.2 GET `/users/{userId}/categories` — 특정 사용자의 카테고리 목록 조회

#### Path Parameter

- `userId`: 사용자 ID

#### Response

```json
[
  {
    "id": 1,
    "name": "일반 카테고리"
  },
  {
    "id": 2,
    "name": "테스트 카테고리"
  }
]
```

---

### 2.3 GET `/categories/{categoryId}` — 카테고리 단건 조회

#### Path Parameter

- `categoryId`: 조회할 카테고리 ID

#### Response

```json
{
  "id": 1,
  "name": "일반 카테고리"
}
```

---

### 2.4 PATCH `/categories/{categoryId}` — 카테고리 이름 수정

#### Path Parameter

- `categoryId`: 수정할 카테고리 ID

#### Request Body

```json
{
  "name": "변경된 카테고리"
}
```

#### Response

```json
{
  "id": 1,
  "name": "변경된 카테고리"
}
```

---

### 2.5 DELETE `/categories/{categoryId}` — 카테고리 삭제

#### Path Parameter

- `categoryId`: 삭제할 카테고리 ID

#### Response

```json
{
  "success": true,
  "data": null,
  "error": null
}
```

---

## Tasks (업무)

| Method | Endpoint                | Description        |
|--------|-------------------------|--------------------|
| POST   | `/users/{userId}/tasks` | 업무 생성              |
| GET    | `/users/{userId}/tasks` | 특정 사용자의 모든 업무 조회   |
| GET    | `/tasks/{taskId}`       | 업무 단건 조회           |
| PATCH  | `/tasks/{taskId}`       | 업무 수정              |
| DELETE | `/tasks/{taskId}`       | 업무 삭제(Soft Delete) |

---

### 3.1 POST `/users/{userId}/tasks` — 업무 생성

#### Path Parameter

- `userId`: 업무를 생성할 사용자 ID

#### Request Body

```json
{
  "title": "테스트업무",
  "description": "업무에 대한 설명",
  "priority": 1,
  "status": "TODO",
  "dueDate": "2025-12-10",
  "categoryId": 2
}
```

#### Response

```json
{
  "id": 5,
  "title": "테스트업무",
  "description": "업무에 대한 설명",
  "priority": 1,
  "status": "TODO",
  "dueDate": "2025-12-10",
  "categoryId": 2
}
```

---

### 3.2 GET `/users/{userId}/tasks` — 사용자의 전체 할 일 조회

#### Path Parameter

- `userId`: 업무를 조회할 사용자 ID

#### Response

```json
[
  {
    "id": 5,
    "title": "테스트업무",
    "description": "업무에 대한 설명",
    "priority": 1,
    "status": "TODO",
    "dueDate": "2025-12-10",
    "categoryId": 2
  }
]
```

---

### 3.3 GET `/tasks/{taskId}` — 업무 단건 조회

#### Path Parameter

- `taskId`: 조회할 업무 ID

#### Response

```json
[
  {
    "id": 5,
    "title": "테스트업무",
    "description": "업무에 대한 설명",
    "priority": 1,
    "status": "TODO",
    "dueDate": "2025-12-10",
    "categoryId": 2
  }
]
```

---

### 3.4 PATCH `/tasks/{taskId}` — 업무 수정

#### Path Parameter

- `taskId`: 수정할 업무 ID

#### Request Body

```json
{
  "title": "수정 테스트업무",
  "description": "수정된 업무에 대한 설명",
  "priority": 3,
  "status": "IN_PROGRESS",
  "dueDate": "2025-12-15"
}
```

#### Response

```json
[
  {
    "id": 5,
    "title": "수정 테스트업무",
    "description": "수정된 업무에 대한 설명",
    "priority": 3,
    "status": "IN_PROGRESS",
    "dueDate": "2025-12-15",
    "categoryId": 2
  }
]
```

---

### 2.5 DELETE `/tasks/{taskId}` — 업무 삭제

#### Path Parameter

- `taskId`: 삭제할 업무 ID

#### Response

```json
{
  "success": true,
  "data": null,
  "error": null
}
```

---

# 추가 예정 (v0.5.0 확장 기능)

아래 기능은 ERD 확장(v0.5.0) 작업 후 엔드포인트가 추가될 예정입니다.

- Tag
- TaskTag
- Notification
- RecurringRule

---

# Version History

## [v0.2.1] — 2025-12-11

### Added

- User 수정, 삭제 작성

---

## [v0.2.0] — 2025-12-08

### Added

- User/Category/Task 전체 엔드포인트 상세 버전 작성
- Request/Response JSON 샘플 추가

---

## [v0.1.0] — 2025-12-05

### Added

- 최초 API 엔드포인트 문서 작성

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

## Users (유저)

| Method | Endpoint    | Description      |
|--------|-------------|------------------|
| GET    | `/users/me` | 내 정보 조회          |
| PATCH  | `/users/me` | 사용자 정보 일부 수정(선택) |

---

## Tasks (업무)

| Method | Endpoint                 | Description          |
|--------|--------------------------|----------------------|
| GET    | `/tasks`                 | Task 목록 조회           |
| POST   | `/tasks`                 | Task 생성              |
| GET    | `/tasks/{taskId}`        | Task 상세 조회           |
| PUT    | `/tasks/{taskId}`        | Task 전체 수정           |
| PATCH  | `/tasks/{taskId}`        | Task 일부 수정           |
| PATCH  | `/tasks/{taskId}/status` | Task 상태 변경 (완료/미완료)  |
| DELETE | `/tasks/{taskId}`        | Task 삭제(Soft Delete) |

---

## Categories (카테고리)

| Method | Endpoint                   | Description |
|--------|----------------------------|-------------|
| GET    | `/categories`              | 카테고리 목록 조회  |
| POST   | `/categories`              | 카테고리 생성     |
| GET    | `/categories/{categoryId}` | 카테고리 상세 조회  |
| PUT    | `/categories/{categoryId}` | 카테고리 수정     |
| DELETE | `/categories/{categoryId}` | 카테고리 삭제     |

---

# 추가 예정 (v0.5.0 확장 기능)

아래 기능은 ERD 확장(v0.5.0) 작업 후 엔드포인트가 추가될 예정입니다.

- Tag
- TaskTag
- Notification
- RecurringRule

---

# Version History

## [v0.1.0] — 2025-12-05

### Added

- 최초 API 엔드포인트 문서 작성

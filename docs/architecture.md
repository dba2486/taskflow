# TaskFlow Architecture

TaskFlow는 개인 일정과 카테고리 기반 업무(Task)를 효율적으로 관리하기 위한 시스템이며,  
확장 가능한 구조를 목표로 설계되었습니다.  
본 문서는 프로젝트 전체 구조, 계층별 책임, 전역 정책(Global Layer), 개발 규칙, 계층 트리를 정의합니다.

---

# 1. 전체 프로젝트 구조

TaskFlow는 **Layered Architecture(계층형 아키텍처)** 기반으로 설계되었으며  
Controller → Service → Repository → Entity 흐름을 따른다.

아래는 프로젝트의 전체 디렉토리 구조이다.

```text
taskflow/
 ├─ src/
 │   ├─ main/
 │   │   ├─ java/com/taskflow/
 │   │   │   ├─ api/                # Controller 계층
 │   │   │   │    ├─ user/
 │   │   │   │    ├─ task/
 │   │   │   │    ├─ category/
 │   │   │   │    └─ auth/
 │   │   │   │
 │   │   │   ├─ domain/             # Entity + Repository
 │   │   │   │    ├─ user/
 │   │   │   │    ├─ task/
 │   │   │   │    ├─ category/
 │   │   │   │    ├─ tag/           # (v0.5.0 예정)
 │   │   │   │    └─ common/        # BaseEntity 등 공통 엔티티
 │   │   │   │
 │   │   │   ├─ service/            # Business Logic
 │   │   │   │    ├─ user/
 │   │   │   │    ├─ task/
 │   │   │   │    └─ category/
 │   │   │   │
 │   │   │   ├─ global/             # 공통 인프라
 │   │   │   │    ├─ config/        # Security, Swagger 등 공통 설정
 │   │   │   │    ├─ exception/     # 공통 예외 처리
 │   │   │   │    └─ response/      # 공통 응답 구조
 │   │   │   │
 │   │   │   └─ TaskflowApplication.java
 │   │   │
 │   │   ├─ resources/
 │   │   │    ├─ application.yml
 │   │   │    ├─ schema.sql (옵션)
 │   │   │    └─ data.sql (옵션)
 │   │   │
 │   ├─ test/
 │       └─ java/com/taskflow/
 │            ├─ api/
 │            ├─ service/
 │            └─ domain/
 │
 ├─ docs/
 │   ├─ feature-list.md
 │   ├─ requirements.md
 │   ├─ api-spec.md
 │   ├─ erd-v0.1.0.png
 │   ├─ erd-v0.5.0.png
 │   └─ architecture.md
 │
 ├─ README.md
 └─ build.gradle or pom.xml
```

---

# 2. 계층별 역할 (Layered Architecture)

## 2.1 Presentation Layer — Controller (/api)

- HTTP 요청/응답 처리
- Request/Response DTO 매핑
- @Valid 검증
- 비즈니스 로직 금지
- 서비스 호출을 통한 기능 수행

예시:

```
api/user/UserController.java
api/task/TaskController.java
api/category/CategoryController.java
```

---

## 2.2 Application Layer — Service (/service)

- 비즈니스 로직 처리
- 트랜잭션 관리(@Transactional)
- 도메인 규칙 구현
- Controller와 Repository 연결

예시:

```
service/task/TaskService.java
service/task/TaskServiceImpl.java

```

---

## 2.3 Domain Layer — Entity & Repository (/domain)

### ✔ Entity

- DB 테이블 매핑
- 도메인의 상태/행위를 표현
- BaseEntity로 createdAt, updatedAt 공통 처리

### ✔ Repository

- JPA 기반 데이터 접근
- 단순 CRUD는 JpaRepository
- QueryDSL 확장 가능

예시:

```
domain/task/Task.java
domain/task/TaskRepository.java
```

---

# 3. Infrastructure Layer — Global(/global)

## 3.1 Config

- Security 설정
- Swagger(SpringDoc) 설정
- JWT 설정 (추후)

## 3.2 Exception

- 전역 예외 처리(GlobalExceptionHandler)
- CustomException
- ErrorResponse 구조

## 3.3 Response

- 공통 API 성공/실패 응답 구조

예시:

```json
{
  "success": true,
  "data": {}
}
```

```json
{
  "success": false,
  "error": {
    "code": "TASK_NOT_FOUND",
    "message": "Task not found"
  }
}
```

---

# 4. 계층 구조 트리 (Layer Tree)

아래는 프로젝트 전체 계층 구조를 시각적으로 표현한 트리입니다.

```text
TaskFlow Architecture
├── Presentation Layer (API)
│   ├── UserController
│   ├── TaskController
│   ├── CategoryController
│   └── AuthController (예정)
│
├── Application Layer (Service)
│   ├── UserService / UserServiceImpl
│   ├── TaskService / TaskServiceImpl
│   └── CategoryService / CategoryServiceImpl
│
├── Domain Layer (Entity + Repository)
│   ├── Entities
│   │   ├── User
│   │   ├── Task
│   │   ├── Category
│   │   └── (v0.5.0 예정) Tag, Notification, RecurringRule
│   │
│   └── Repositories
│       ├── UserRepository
│       ├── TaskRepository
│       └── CategoryRepository
│
└── Infrastructure Layer (Global)
    ├── config
    ├── exception
    └── response

```

---

# 5. 아키텍쳐 흐름도

```text
Client
  ↓
Controller (API Layer)
  ↓
Service (Business Layer)
  ↓
Repository (Data Access Layer)
  ↓
MySQL (Database)
```

---

# 6. 개발 규칙 (Coding Conventions)

### ✔ Controller

- 비즈니스 로직 금지
- DTO 변환 위주 처리

### ✔ Service

- 트랜잭션 경계 명확히
- 도메인 규칙 처리

### ✔ Repository

- JpaRepository 기본 제공 기능 활용
- 필요 시 QueryDSL 사용 고려

### ✔ DTO

- Request / Response 분리
- Entity 직접 노출 금지

---

# 7. 확장성 고려 (v0.5.0 예정)

- Tag / TaskTag (N:N 관계)
- 알림(Notification)
- 반복 규칙(RecurringRule)
- OAuth2 Login

현재 구조는 도메인 중심 계층 구조이므로,\
__domain / service / api 계층만 확장하면 기능 추가가 용이하여 유지보수성이 높음.__

---

# Version History

| Version | Date       | Description  |
|---------|------------|--------------|
| v0.1.0  | 2025-12-06 | 초기 패키지 구조 작성 |
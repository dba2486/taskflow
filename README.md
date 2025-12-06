# TaskFlow

개인 일정과 업무(Task)를 효율적으로 관리하는 **업무 관리 서비스(To-Do / Task Manager)** 입니다.  
카테고리 분류, 일정 관리, 소프트 삭제, 반복 작업(예정), 태그 시스템(예정) 등  
확장 가능한 구조를 목표로 개발되었습니다.

---

## Tech Stack

### Backend

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Spring Validation
- Spring Security (예정)
- MySQL 8.x
- QueryDSL (예정)
- Lombok

### DevOps / Tools

- Gradle
- Git / GitHub
- Docker (예정)
- IntelliJ IDEA

---

## Project Structure

```text
taskflow/
 ├─ src/main/java/com/taskflow/
 │    ├─ api/            # Controller
 │    ├─ service/        # Business Logic
 │    ├─ domain/         # Entities & Repositories
 │    └─ global/         # Config, Exception, Response
 │
 ├─ src/main/resources/
 │    └─ application.yml
 │
 ├─ docs/                # 요구사항, ERD, API 문서
 ├─ build.gradle
 └─ README.md
```

---

## 주요 기능 (v0.1.0)

### ✔ User

- 회원 가입
- 로그인 / 인증 기능은 v0.5.0에서 제공 예정

### ✔ Category

- 카테고리 생성 / 조회 / 삭제
- 사용자의 Task를 그룹화

### ✔ Task

- Task 생성
- Task 단건 조회
- Task 목록 조회
- Task 수정
- Task 삭제 (Soft Delete)
- 마감일(dueDate) 설정
- 완료 여부 처리

---

## 시스템 아키텍처

### 계층형 구조 (Layered Architecture)

```text
Controller (API Layer)
    ↓
Service (Business Logic)
    ↓
Repository (Data Access Layer)
    ↓
MySQL (Database)
```

### 도메인 ERD (v0.1.0)

docs/erd-v0.1.0.png 참고 (v0.5.0: Tag, Notification, RecurringRule 예정)

---

## API 문서

모든 엔드포인트는 `docs/api-spec.md` 에 정리되어 있습니다.

### 주요 Endpoint 예시:

__Task 예시__

| Method | Endpoint          | Description      |
|--------|-------------------|------------------|
| POST   | `/api/tasks/`     | Task 생성          |
| GET    | `/api/tasks/{id}` | Task 단건 조회       |
| GET    | `/api/tasks`      | Task 목록 조회       |
| PATCH  | `/api/tasks/{id}` | Task 수정          |
| DELETE | `/api/tasks{id}`  | Task Soft Delete |

---

## Domain Model

- `User` (1) — (N) `Task`
- `Category` (1) — (N) `Task`
- Task는 Soft Delete 지원
- BaseEntity 상속으로 생성/수정일 자동 관리

자세한 구조는 `docs/architecture.md`참고

---

## 개발 규칙 (Coding Convention)

### ✔ Controller

- DTO 변환 위주 처리, 비즈니스 로직 금지

### ✔ Service

- @Transactional 기반 비즈니스 로직 처리
- 도메인 규칙 중심 개발

### ✔ Repository

- JpaRepository 활용
- 필요 시 QueryDSL로 확장

### ✔ DTO

- Request / Response 분리
- Entity 직접 노출 금지

---

## v0.5.0 업데이트 예정 기능

- 태그(Tag) 기능
- 반복 작업(RecurringRule)
- 알림(Notification)
- OAuth2 로그인
- QueryDSL 기반 조회 고도화

---

## 로컬 실행 방법

### 1. Clone

```bash
git clone https://github.com/dba2486/taskflow.git
```

### 2. 환경 변수 설정

`src/main/resources/application.yml` 내부에 DB 정보 작성:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/taskflow
    username: root
    password: 비밀번호
```

### 3. 실행

```bash
./gradlew bootRun
```

---

## 개발자

__김종호 (Jongho Kim)__\
Backend Developer — Java/Spring Boot\
GitHub: https://github.com/dba2486

---
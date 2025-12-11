# TaskFlow 요구사항 정의서 (v0.1.0)

본 문서는 TaskFlow 프로젝트의 v0.1.0 요구사항을 정의한다.  
핵심 기능은 **업무(Task) 관리**, **카테고리 관리**, **사용자 인증(Auth)** 으로 구성된다.

---

# 1. 시스템 개요

사용자는 회원가입 및 로그인을 통해 자신의 계정을 만들고,  
업무(Task)를 생성/조회/수정/삭제할 수 있다.  
업무는 카테고리에 속할 수 있으며, 카테고리 또한 사용자가 직접 생성/관리한다.

---

# 2. 요구사항 목록

## 2.1 사용자(User) 기능

### **[U-01] 회원가입**

- 사용자는 이메일과 비밀번호를 입력해 계정을 생성할 수 있다.
- 이메일은 중복 가입이 불가능하다.
- 비밀번호는 4~100자로 입력해야한다.
- 비밀번호는 암호화하여 저장해야 한다.
- 회원가입 성공 시 `id, name, email` 정보를 반환한다.
- 실패 조건:
    - 이메일 형식 오류
    - 이미 존재하는 이메일
    - 비밀번호 길이 부족

---

### **[U-02] 로그인**

- 사용자는 이메일 + 비밀번호로 로그인을 할 수 있다.
- 인증에 성공하면 JWT Access Token을 발급받는다.
- 실패 조건:
    - 존재하지 않는 이메일
    - 비밀번호 불일치

---

### **[U-03] 내 정보 조회**

- 로그인한 사용자는 자신의 정보를 조회할 수 있다.

---

### **[U-04] 사용자 정보 수정**

- 사용자는 자신의 이름과 비밀번호를 수정할 수 있다.

---

### **[U-05] 사용자 삭제**

- 사용자는 자신의 계정을 삭제할 수 있다.
- 사용자 삭제는 Hard delete를 수행한다.

# 2.2 업무(Task) 기능

### **[T-01] Task 생성**

- 사용자는 새로운 Task를 생성할 수 있다.
- 필드:
    - title (필수)
    - description (선택)
    - due_date (필수)
    - priority (필수)
    - status (필수)
    - category_id (필수)
- Task는 생성 시 자신의 user_id와 연결된다.
- status는 enum(TaskStatus)로 관리한다.
- 실패 조건:
    - title 미입력
    - due_date 미입력
    - due_date 올바르지 못한 형식으로 입력
    - priority 미입력
    - status 미입력
    - category_id 미입력
    - 존재하지 않는 category_id 사용

---

### **[T-02] Task 목록 조회**

- 사용자는 자신의 모든 Task를 조회할 수 있다.
- soft delete된 Task는 목록에서 제외한다.
- 기본 정렬: 생성일 내림차순(created_at DESC)
- 추가 필터(선택):
    - category_id
    - status (TODO / DONE)
    - priority

---

### **[T-03] Task 상세 조회**

- 사용자는 특정 Task의 상세 정보를 조회할 수 있다.
- 단, 자신의 Task만 조회 가능하다.
- deleted=false 상태여야 조회 가능하다.

---

### **[T-04] Task 수정 (전체 수정)**

- 사용자는 Task의 title, description, due_date, priority, status, category_id 등을 수정할 수 있다.
- 유효성:
    - category_id가 존재해야 함
    - 수정은 자신의 Task만 가능함
    - soft delete된 Task는 수정이 불가능하다.
    - status는 enum 값만 허용한다.

---

### **[T-05] Task 부분 수정(PATCH)**

- 사용자는 title 또는 status 등 일부 필드만 수정할 수 있다.

---

### **[T-06] Task 상태 변경**

- 사용자는 Task를 완료/진행중/미완료 상태로 변경할 수 있다.
- 상태 값:
    - TODO
    - IN_PROGRESS
    - DONE
- enum 외 값 전달 시 INVALID_REQUEST exception이 발생한다.
-

---

### **[T-07] Task 삭제 (Soft Delete)**

- 사용자는 Task를 삭제할 수 있다.
- DB에서는 deleted = true 로 표시하며 데이터는 물리적으로 삭제되지 않는다.

---

# 2.3 카테고리(Category) 기능

### **[C-01] 카테고리 생성**

- 사용자는 Task를 분류하기 위한 카테고리를 생성할 수 있다.
- 필드:
    - name (필수)
- 동일 사용자의 카테고리 이름 중복 허용/비허용 여부는 자유 설계  
  → 기본값: 허용

---

### **[C-02] 카테고리 목록 조회**

- 사용자는 자신의 카테고리 목록을 조회할 수 있다.

---

### **[C-03] 카테고리 상세 조회**

- 사용자는 특정 카테고리의 상세 정보를 조회할 수 있다.

---

### **[C-04] 카테고리 수정**

- 사용자는 기존 카테고리 이름을 수정할 수 있다.

---

### **[C-05] 카테고리 삭제**

- 사용자는 카테고리를 삭제할 수 있다.
- 삭제 시 해당 카테고리에 속한 Task의 category_id는 NULL 처리할 수 있다.  
  (정책은 설계에 따라 선택)

---

# 2.4 시스템 공통 요구사항

### **[S-01] 인증(Authorization)**

- 모든 Task 및 Category API는 JWT 인증을 필요로 한다.

---

---

### **[S-02] 에러 응답 형식**

모든 API 에러 응답은 다음 형식을 따른다:

```json
{
  "message": "에러 설명",
  "errorCode": "ERROR_CODE"
}
```

---

### **[S-03] 데이터 생성/수정 시각 관리**

모든 주요 엔티티(Task, Category, User)는 다음 필드를 갖는다:

- created_at (생성 시간)
- updated_at (수정 시간)

Spring Boot JPA Auditing으로 자동 관리한다.

---

### **[S-04] Soft Delete 정책**

Task는 deleted 컬럼(boolean)으로 삭제 여부만 표시한다.

---

# 3. 비기능 요구사항 (NFR)

### **[N-01] 백엔드 프레임워크**

- Spring Boot 3.x

### **[N-02] 데이터베이스**

- MySQL 8.x

### **[N-03] 빌드 도구**

- Gradle

### **[N-04] 인증 방식**

- JWT 기반 인증/인가

### **[N-05] API 문서화**

- v0.1.0: Markdown 기반 문서

---

# 4. 향후 확장(v0.5.0)

아래 기능은 ERD 확장 후 추가 도입 가능하다.

- Tag
- TaskTag (N:N 구조)
- RecurringRule (반복 규칙)
- Notification (Task 알림)

---

# Version History

| Version | Date       | Description                     |
|---------|------------|---------------------------------|
| v0.2.0  | 2025-12-11 | User/Category/Task의 기능별 요구사항 추가 |
| v0.1.0  | 2025-12-05 | 초기 요구사항 정의서 작성                  |

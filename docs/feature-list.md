# TaskFlow – 기능 목록 (Feature List)

TaskFlow는 개인 생산성 향상을 위한 업무 관리 서비스입니다.  
아래는 프로젝트의 필수 기능(v0.1.0)과 선택 기능(v0.5.0)으로 구분된 기능 목록입니다.

---

# 🔵 v0.1.0 (필수 기능)

## 1. 사용자(User) 기능

### 1.1 회원가입(Sign Up)

- 이메일 + 비밀번호 입력
- 비밀번호 암호화(BCrypt)
- 이메일 중복 체크
- Validation 적용

### 1.2 로그인(Login)

- JWT Access Token 발급
- Refresh Token 발급
- Access Token 재발급 기능

### 1.3 내 정보 조회(My Info)

- 사용자 기본 정보 조회(API)

---

## 2. 인증(Auth)

- Spring Security + JWT 기반 인증 구조
- Access/Refresh Token 구조
- 인증 필터 적용
- 비로그인 사용자 접근 제한

---

## 3. 업무(Task) 기능 (핵심)

### 3.1 업무 생성(Create Task)

- 제목
- 설명(optional)
- 마감일(dueDate)
- 우선순위(priority)
- 카테고리(optional)

### 3.2 업무 조회(Read Tasks)

- 전체 목록 조회
- 단일 Task 조회
- 상태(완료/미완료) 필터
- 카테고리 기반 조회

### 3.3 업무 수정(Update Task)

- 제목, 설명, 카테고리, 마감일, 우선순위 수정

### 3.4 업무 삭제(Delete Task)

- 소프트 삭제(Deleted Flag)

### 3.5 업무 상태 변경

- 완료/미완료 상태 토글

---

## 4. 카테고리(Category) 기능

### 4.1 카테고리 생성

### 4.2 카테고리 수정

### 4.3 카테고리 삭제 (Task는 미분류로 이동)

### 4.4 카테고리별 Task 조회

---

## 5. 검색/정렬 기능

### 5.1 검색

- 제목 키워드
- 내용 키워드

### 5.2 정렬

- 생성일 순
- 마감일 순
- 우선순위 순

---

## 6. 시스템 공통 기능

- Global Exception Handler
- Custom Exception 구조
- DTO Validation
- Request/Response 분리
- Logging
- Swagger API 문서 자동화

---

# 🟩 v0.5.0 (선택 기능 – 추후 확장)

## 7. 태그(Tag) 기능

### 7.1 태그 생성/수정/삭제

### 7.2 Task에 태그 추가/삭제

### 7.3 태그 기반 검색

---

## 8. 알림(Notification)

### 8.1 마감일 1일 전 알림

### 8.2 반복 일정 알림

---

## 9. 반복 기능(Recurring Task)

### 9.1 매일/매주/매월 반복 Task 생성

### 9.2 반복 Task 자동 생성 로직

---

## 10. 관리자(Admin) 기능

### 10.1 사용자 리스트 조회

### 10.2 Task 강제 삭제

### 10.3 서버 상태 조회

---

# Version History

| Version | Date       | Description |
|---------|------------|-------------|
| v0.1.0    | 2025-12-05 | 초기 기능목록 작성  |

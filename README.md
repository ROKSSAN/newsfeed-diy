# 📘 뉴스피드 프로젝트 API 명세서

## 🧾 회원가입 & 로그인

### ✅ 회원가입

- **URL**: `POST /api/signup`
- **Request Body**:
```json
{
  "email": "user@example.com",
  "password": "Test1234!",
  "username": "강산"
}
```
- **Response**: `200 OK`
```json
{
  "message": "회원가입 성공"
}
```
- **예외처리**:
  - 400: 이메일 형식 오류 (`"올바른 이메일 형식을 입력해주세요."`)
  - 400: 비밀번호 형식 오류 (`"비밀번호는 최소 8자, 특수문자/대소문자/숫자 포함"` )
  - 409: 이메일 중복 (`"이미 가입된 이메일입니다."`)

---

### ✅ 로그인

- **URL**: `POST /api/login`
- **Request Body**:
```json
{
  "email": "user@example.com",
  "password": "Test1234!"
}
```
- **Response**: `200 OK`
```json
{
  "token": "JWT_ACCESS_TOKEN"
}
```
- **예외처리**:
  - 401: 로그인 실패 (`"이메일 또는 비밀번호가 일치하지 않습니다."`)

---

## 👤 프로필 관리

### ✅ 프로필 조회

- **URL**: `GET /api/members/{memberId}`
- **Response**:
```json
{
  "memberId": 1,
  "username": "강산"
}
```

---

### ✅ 프로필 수정

- **URL**: `PUT /api/members/me`
- **Request Body**:
```json
{
  "username": "새이름"
}
```
- **Response**:
```json
{
  "message": "프로필 수정 완료"
}
```

---

### ✅ 비밀번호 변경

- **URL**: `PUT /api/members/password`
- **Request Body**:
```json
{
  "currentPassword": "Test1234!",
  "newPassword": "Newpass123!"
}
```
- **Response**:
```json
{
  "message": "비밀번호가 변경되었습니다."
}
```
- **예외처리**:
  - 400: 형식 오류
  - 400: 기존 비밀번호와 동일 (`"기존 비밀번호와 동일합니다."`)
  - 401: 현재 비밀번호 불일치 (`"비밀번호가 일치하지 않습니다."`)

---

## 📝 게시물 (Posts)

### ✅ 게시글 작성

- **URL**: `POST /api/posts`
- **Request Body**:
```json
{
  "content": "오늘도 화이팅!"
}
```
- **Response**:
```json
{
  "postId": 1,
  "message": "게시글이 작성되었습니다."
}
```

---

### ✅ 게시글 전체 조회 (페이지네이션)

- **URL**: `GET /api/posts?page=0&size=10`
- **Response**:
```json
[
  {
    "postId": 1,
    "content": "첫 게시물",
    "author": "강산",
    "createdAt": "2025-06-09T10:00:00"
  }
]
```

---

### ✅ 게시글 수정

- **URL**: `PUT /api/posts/{postId}`
- **Request Body**:
```json
{
  "content": "수정된 게시물 내용"
}
```
- **Response**:
```json
{
  "message": "게시글이 수정되었습니다."
}
```
- **예외처리**:
  - 403: 본인만 수정 가능 (`"작성자만 수정할 수 있습니다."`)

---

### ✅ 게시글 삭제

- **URL**: `DELETE /api/posts/{postId}`
- **Response**:
```json
{
  "message": "게시글이 삭제되었습니다."
}
```
- **예외처리**:
  - 403: 본인만 삭제 가능 (`"작성자만 삭제할 수 있습니다."`)

---

## 💬 댓글 (Comments)

### ✅ 댓글 작성

- **URL**: `POST /api/posts/{postId}/comments`
- **Request Body**:
```json
{
  "content": "댓글입니다"
}
```
- **Response**:
```json
{
  "commentId": 1,
  "message": "댓글이 작성되었습니다."
}
```

---

### ✅ 댓글 수정

- **URL**: `PUT /api/comments/{commentId}`
- **Request Body**:
```json
{
  "content": "수정된 댓글입니다"
}
```
- **Response**:
```json
{
  "message": "댓글이 수정되었습니다."
}
```
- **예외처리**:
  - 403: 본인만 수정 가능

---

### ✅ 댓글 삭제

- **URL**: `DELETE /api/comments/{commentId}`
- **Response**:
```json
{
  "message": "댓글이 삭제되었습니다."
}
```
- **예외처리**:
  - 403: 본인만 삭제 가능

---

## ❤️ 좋아요 (Likes)

### ✅ 게시글 좋아요

- **URL**: `POST /api/posts/{postId}/like`
- **Response**:
```json
{
  "message": "좋아요 등록 완료"
}
```

---

### ✅ 댓글 좋아요

- **URL**: `POST /api/comments/{commentId}/like`
- **Response**:
```json
{
  "message": "좋아요 등록 완료"
}
```

---

### ✅ 좋아요 취소

- **URL**: `DELETE /api/likes/{likeId}`
- **Response**:
```json
{
  "message": "좋아요가 취소되었습니다."
}
```
- **예외처리**:
  - 400: 본인 게시물/댓글 좋아요 불가
  - 409: 중복 좋아요 방지

---

## 👥 팔로우 (Follow)

### ✅ 팔로우 하기

- **URL**: `POST /api/members/{memberId}/follow`
- **Response**:
```json
{
  "message": "팔로우 완료"
}
```

---

### ✅ 언팔로우

- **URL**: `DELETE /api/members/{memberId}/unfollow`
- **Response**:
```json
{
  "message": "언팔로우 완료"
}
```

---

### ✅ 팔로잉 피드 조회

- **URL**: `GET /api/posts/following?page=0&size=10`
- **Response**:
```json
[
  {
    "postId": 5,
    "author": "팔로우한사람",
    "content": "팔로우한 사람의 글",
    "createdAt": "2025-06-09T11:00:00"
  }
]
```

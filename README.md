# NBC_week6_1

* 작성한 API 명세
  * 링크: [노션 링크](https://www.notion.so/06-28-lv-3-95-0b889f8485c6439580ca3ef0c7562a64)
 
====================================== 
 1. USER API

    a. 회원 가입 API (/api/user/signup)
    - username과 password, 그리고 role("ADMIN"과 "USER"만 입력 가능)을 json 형식으로 보내 글자 수, 입력 제한 등에 대한 유효성 검사를 하고 적합하면 DB에 저장합니다.
 
    b.로그인 API (/api/user/login)
    - username과 password를 json 형식으로 보내 글자 수, 입력 제한 등에 대한 유효성 검사와 DB 존재 유무를 판단하여 존재한다면 JWT를 생성하여 이를 통해 제한된 기능(게시, 수정, 삭제)을 사용 가능하게 합니다.

 2. POST API
 
    a. 전체 조회 (/api/post)      --  토큰 없이 api 만으로도 기능 사용 가능
 
    b. 선택 조회 (/api/post/{id}) -- 토큰 없이 api 만으로도 기능 사용 가능
 
    c. 글 생성  (/api/post)        -- header에 JWT를 등록하고 title과 contents를 json 형식으로 전송하여 글 생성
 
    d. 글 수정  (/api/post/{id})   -- header에 JWT를 등록하고 수정할 title과 contents를 json 형식으로, 수정할 글 번호를 @PathVariable로 전송하여 글 수정
 
    e. 글 삭제  (/api/post/{id})   -- header에 JWT를 등록하고 삭제할 글 번호를 @PathVariable로 전송하여 글 삭제

3. COMMENT API

   a. 댓글 생성 (/api/comment) -- header에 JWT를 등록하고 postId와 content를 json 형식으로 전송하여 댓글 생성

   b. 댓글 수정 (/api/comment/{id}) -- header에 JWT를 등록하고 수정할 postId와 content를 json 형식으로, 수정할 댓글 번호를 @PathVariable로 전송하여 댓글 수정

   c. 댓글 삭제 (/api/comment/{id}) -- header에 JWT를 등록하고 삭제할 댓글 번호를 @PathVariable로 전송하여 댓글 삭제

 ====================================== 

* `구현되지 않은 점`

1. 로그인 실패 시 예외 처리와 성공 시 API result 반환 둘 다 못함
   
    a. 로그인 성공 시 statusCode와 statusMessage를 출력하지 않음
   
    b. 로그인 실패 시 그 원인이 `INVALID_TYPE_VALUE`인지 `WRONG_PASSWORD`인지 Client에 반환하지 않음
2. 토큰 유효성 예외 처리가 Client로 반환되지 않고, local의 터미널에서만 출력됨

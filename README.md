# NBC_week6_1

* 작성한 API 명세
  * 링크: https://www.notion.so/06-20-6-1-4c5c4929fb6d40b49b4d71401696dfd0?pvs=4#856ae5d6490744d58544c71d6a6eb54b
 1. 회원 가입 API (/api/user/signup)
    - username과 password를 json 형식으로 보내 글자 수, 입력 제한 등에 대한 유효성 검사를 하고 적합하면 DB에 저장합니다.
 2. 로그인 API (/api/user/login)
    - username과 password를 json 형식으로 보내 글자 수, 입력 제한 등에 대한 유효성 검사와 DB 존재 유무를 판단하여 존재한다면 JWT를 생성하여 이를 통해 제한된 기능(게시, 수정, 삭제)을 가능하게 합니다.
 3. POST API
    a. 전체 조회 (/api/posts)
      - 토큰 없이 api 만으로도 기능 사용 가능
    b. 선택 조회 (/api/posts/{id})
      - 토큰 없이 api 만으로도 기능 사용 가능
    c. 글 게시  (/api/post)
      - header에 JWT를 등록하고 title과 contents를 json 형식으로 전송하여 글 게시
    d. 글 수정  (/api/post/{id})
      - header에 JWT를 등록하고 수정할 title과 contents를 json 형식으로 전송하여, 수정할 글 번호를 @PathVariable로 전송하여 글 수정
    e. 글 삭제  (/api/post/{id})
      - header에 JWT를 등록하고 삭제할 글 번호를 @PathVariable로 전송하여 글 삭제

* 질문 답변
1. 처음 설계한 API 명세서에 변경사항이 있었나요? 변경 되었다면 어떤 점 때문 일까요? 첫 설계의 중요성에 대해 작성해 주세요!
   => `처음 설계했던 API에서 변경된 것은 AuthFilter에서 인증이 필요한 API와 필요하지 않은 것을 구분하기 위해 Post API에서 전체/선택 조회의 route의 시작 경로를 /api/posts로, 게시물 작성, 수정, 삭제의 시작 경로를 /api/post로 통일시킨 것입니다. 이것은 프로젝트 구현 시작 초반에 Spring security를 제대로 알지 못해 일어난 miss라고 생각했고, 프로젝트 구현 전에RESTful API 설계도 고민해야 겠다고 생각했습니다.`
    

2. ERD를 먼저 설계한 후 Entity를 개발했을 때 어떤 점이 도움이 되셨나요?
   => `User와 Post class의 필수 필드들에 대한 이해를 팀원들과 공유할 수 있다는 것이 잠재적인 장점이라고 생각했고, 또한 이를 활용하는 Dto를 설계할 때도 필요한 필드들만 추출/활용 할 수 있어 도움이 되었습니다.`
    
3. JWT를 사용하여 인증/인가를 구현 했을 때의 장점은 무엇일까요?
    => `데이터의 위변조를 방지하며, 세션에 비해 구현 절차가 쉽고, 그에 따라 유지보수 및 재사용도 수월하다.`
    
4. 반대로 JWT를 사용한 인증/인가의 한계점은 무엇일까요?
    => `쿠키/세션과 다르게 토큰의 길이가 길어서 인증 요청이 많아질 수록 네트워크 부하가 심해지며, 토큰을 탈취 당하면 대처가 매우 어렵다.`

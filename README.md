![test coverage](.github/badges/jacoco.svg)

# Project: LiMu(Listen To This Music)_README.MD

## :hammer: *주제*

youtube music, melon 등 음악 스트리밍 서비스를 통해 사용자들이 편하게 음악 청취를 하고 있다.

음악 스트리밍 서비스를 통해 사용자들이 다양한 음악을 접하고 있지만, 자신이 좋아하는 음악을 추천해주거나 특정 음악에 대해 자유롭게 의견을 나누는 공간은 비교적 부족한 상황이다.

이와 같은 이유로 해당 프로젝트를 통해 사용자들이 서로 음악을 추천해주고, 의견을 나누면서 보다 풍성하게 음악 청취를 할 수 있는 서비스를 개발해보고자 한다.  

## :hammer: *사용 기술 스택*
<div align="center">
  <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
  <img src="https://img.shields.io/badge/spring Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white">
  <img src="https://img.shields.io/badge/spring JPA-6DB33F?style=for-the-badge&logo=&logoColor=white">
  <br>
  <img src="https://img.shields.io/badge/thymeleaf-005F0F?style=for-the-badge&logo=thymeleaf&logoColor=white">
  <img src="https://img.shields.io/badge/mariadb-003545?style=for-the-badge&logo=mariadb&logoColor=white">
  <br>
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=CSS3&logoColor=white">
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=JavaScript&logoColor=white">
</div>
 

## :hammer: *기능*

해당 서비스 기능 사용자는 크게 관리자와 일반 회원으로 구분된다.

관리자와 일반 회원이 공통 기능을 사용할 수 있으며, 관리자에게 추가 기능을 부여하고자 한다.

그 외 주요 서비스 기능으로는 '필터링을 통한 음악 검색 기능', '오늘 날씨와 어울리는 음악 추천 기능'이 있다.

### 1. 관리자 기능

- **회원 관리 기능**
    - [ ] 회원 목록 조회
    - [ ] 회원 상세 정보 조회
    - [ ] 회원 상태 수정 (가입 승인, 정지 등)
- **게시글 관리 기능**
    - [ ] 타인(일반 회원)의 게시글 삭제, 수정 기능

### 2. 공통 기능

- **회원 가입 기능**
    - [ ] 아이디(이메일)을 통한 중복 가입 방지
    - [ ] 이메일을 통한 가입 인증
- **회원 탈퇴 기능**
    - [ ] 이메일 인증을 통한 회원 탈퇴 기능
- **회원 정보 수정 기능**
    - [ ] 닉네임, 연락처 수정 가능
    - [ ] 그 외 수정 불가능
- **비밀번호 초기화 기능**
    - [ ] 가입 이메일을 통한 비밀번호 초기화
- **게시글 기능**
    - [ ] 게시글 등록, 삭제, 수정 기능
- **댓글 기능**
    - [ ] 게시글 댓글 기능 (의견, 평점)
- **신고 기능**
    - [ ] 타인의 게시글, 댓글을 신고하는 기능
- **쪽지 기능**
    - [ ] 타인과 실시간으로 쪽지를 주고 받을 수 있는 기능
- **이벤트 기능**
    - [ ] 타인이 자신에게 쪽지를 보내거나, 자신의 글에 댓글을 달았을 때 실시한으로 확인할 수 있는 기능

### 3. 그 외 서비스 기능

- **필터링 검색**
    - [ ] 게시글 제목 및 내용을 통한 검색
    - [ ] 태그, 평점, 댓글 개수 등을 통한 필터링 검색
- **오늘 날씨에 어울리는 음악 추천**
  - [ ] 날씨 openAPI를 통해 오늘 날씨 데이터를 수집
  - [ ] 오늘 날씨에 어울리는 음악 추천 목록 제공
## :hammer: *DB 구조*  
![project ERD](img/Limu_ERD_3.png)
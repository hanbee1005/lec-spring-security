# lec-Spring-Security
인프런 "스프링 시큐리티" 강좌 따라하기

## 화면 설명
- index: 첫 화면으로 누구나 접근 가능 (로그인 여부에 따라 보여지는 메시지 구분)
- info: index와 마찬가지로 누구나 접근 가능
- dashboard: 로그인한 사용자만 접근 가능
- admin: 로그인한 사용자 중 admin 권한을 가진 사람만 접근 가능

## 스프링 시큐리티 연동
아래와 같이 build.gradle 파일에 spring boot 가 제공하는 spring security 의존 설정을 추가
```
implementation 'org.springframework.boot:spring-boot-starter-security'
```

스프링 시큐리티 의존성을 추가하고 나면
- 모든 요청은 인증을 필요로 하게 되고
- 기본 유저가 생성됩니다.
    + Username: user
    + Password: 콘솔에 출력된 문자열 확인
    
해결된 문제
- 인증을 할 수 있다.
- 현재 사용자 정보를 알 수 있다.

새로운 문제
- 인증없이 접근 가능한 URL을 설정하고 싶다.
- 애플리케이션을 사용할 수 있는 유저 계정이 user 하나 뿐이다.
- 비밀번호가 로그에 남는다.
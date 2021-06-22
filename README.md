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

다시 애플리케이션을 실행하고 접근하는 경우 자동으로 로그인 페이지로 이동하게 되며, 기본적으로 Username은 user, Password는 console 창에서 확인 가능   

But, 관리자 화면에 대한 권한 확인은 이루어지지 않은 상태...
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

## 스프링 시큐리티 설정
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .mvcMatchers("/", "/info").permitAll()   // /, /info 요청에 대해서는 모두 허용
            .mvcMatchers("/admin").hasRole("ADMIN")  // /admin 요청에 대해서는 Role이 ADMIN인 사용자만 허용
            .anyRequest().authenticated()            // 그 외 다른 요청에 대해서는 인증(로그인 여부)만 확인
            .and()
            .formLogin()  // 제공되는 formLogin 기능 사용 설정
            .and()
            .httpBasic();  // 제공되는 httpBasic 기능 사용 설정
  }
}
```

해결된 문제
- url 별로 어떤 요청을 어떤 식으로 받아서 처리할지 설정
  + 인증없이 접근 가능한 URL을 설정 가능
  
## 스프링 시큐리티 인메모리 유저 추가
```java
@Override
protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.inMemoryAuthentication()
            .withUser("keesun").password("{noop}123").roles("USER").and()
            .withUser("admin").password("{noop}!@#").roles("ADMIN");
}
```
USER 권한을 가진 keesun이라는 사용자와 ADMIN 권한을 가진 admin 사용자 추가     
password 저장 시 "{암호화_방식}암호화할_문자열" 을 사용하는데 {noop}은 암호화를 하지 않는다는 의미

## JPA 연동
1. spring-data-jpa 및 h2 데이터베이스 의존 추가
```
implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
runtimeOnly 'com.h2database:h2'
```

2. account entity 생성
   
3. repository 인터페이스 생성

4. service 생성하고 UserDetailsService 구현
```java
@Service
@Transactional
public class AccountService implements UserDetailsService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }

        return User.builder()
                .username(account.getUsername())
                .password(account.getPassword())
                .roles(account.getRole())
                .build();
    }

    ...
}
```

5. 회원가입을 위한 controller 매핑 추가

6. spring security에서 받아들이는 패스워드 형식에 맞게 임시 인코딩 기능 추가
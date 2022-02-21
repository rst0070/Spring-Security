# Spring Security by amigos code
[youtube video](https://youtu.be/her_7pa0vrg)  
  
There are many type of authentication
* Form based
* basic auth


## Build API
build api about student information.  
Student objects have it's id(Integer), name(String).  
to do  
* student controller(include temporal data)
* student entity  

## Install Spring Security
Just add `spring-boot-starter-security` to pom.xml  

## Form based Authentication Overview
spring security offers basic form authentication(user name, password) to all request. 
when get authentication, returns response.  
`/logout` offers logout action.  

## Basic auth overview
basic auth ask username and password for every http request.  
so, every request has username and password at header.  
  
if the request has not that header, then server response 401 Unauthorized.  

## Basic auth implementation
using configuration, override `WebSecurityConfigurerAdapter:configure(HttpSecurity)`.
```java
@Configuration      //
@EnableWebSecurity  //web security 를 사용한다
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .anyRequest()   
            .authenticated()
            .and()
            .httpBasic();   //basic authentication을 사용한다.
    }
}
```
  
## ANT Matchers
[section 참고](https://www.section.io/engineering-education/springboot-antmatchers/#:~:text=The%20antMatchers()%20is%20a,specific%20URLs%20as%20its%20arguments.)
특정 경로에 대해 권한을 요구하지않는 white list를 만들수 있다.  
위의 코드에서
```java
    http
        .authorizeRequests()
        .antMathers(
            "/", "/index"
        )
        .permitAll()
        .anyRequest()   
        .authenticated()
        .and()
        .httpBasic();
```

## User 만들기
User에 대한 정보는 UserDetailsService에서 처리함
때문에 이 빈을 만들어줄 configuration이 필요한데 `WebSecurityConfigurerAdapter:userDetailsService`가 그역할을 함
이를 오버라이드하여 만들자.
```java
    @Override
    @Bean
    protected UserDetailsService userDetailsService(){
        UserDetails wonbin = User.builder()
            .username("wonbin")
            .password("password")
            .roles("STUDENT")
            .build();
        
            return new InMemoryUserDetailsManager(
                wonbin
            );
    }
```
위의 `org.springframework.security.core.userdetails.UserDetailsService`를 상속하여 확장한것이
`org.springframework.security.provisioning.UserManager`이고 이를 구현한것이
`org.springframework.security.provisioning.InMemoryUserDetailsManager`  
  
위의 상태에서만 실행하면 password encoder가 없다고 뜸.  

## PasswordEncoder
패스워드 인코더는 패스워드를 특정 알고리즘으로 인코딩 할 수 있게 해준다.
1. 패스워드 인코더 빈을 생성하기(configuration을 통해 가능)
2. user의 정보중 패스워드에 인코더를 적용하기  
  
__encoder 빈 적용__  
```java
@Bean
public PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder(10);
}
```  
  
__user 정보중 패스워드에 인코더적용__
```java
UserDetails wonbin = User.builder()
        .username("wonbin")
        .password(passwordEncoder.encode("password"))
        .roles("STUDENT")
        .build();
```
  
## Roles and Permissions
role은 무엇인가? permission은 무엇인가?  
  
## Role based authentication
ant matcher를 통해 특정 경로를 지정한 뒤
`.hasRole()`을 이용하여 role의 이름을 전달하면 된다.
그러면 특정 role을 가진 사용하자만 해당 경로에 대한 요청을 보낼 수 있다.  

## Permission based authentication
role처럼 특정 경로, method에 (ant matcher를 이용하여) permission에 따른 접근허가 기능을 만들 수 있다.  
```
    ...
    .antMatcher(...).hasAuthority("...")
```
또한 이 permission은 user를 생성할때 등록할 수 있다.  
`authorities(Collection<? extends GrantedAuthority> authorities)`를 이용함.



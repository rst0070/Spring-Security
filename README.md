# Spring security 인증 구조
목자
1. 소개
2. SecurityContextHolder - 누가 인증되었는지 저장
3. SecurityContext - ContextHolder에 저장되며 자신이 가르키는 유저의 Authentication 가짐
4. Authentication - AuthenticationManager에 전달하는 객체 (사용자 자격증명 용도 or 현재 context에 있는 사용자 정보)
5. GrantedAuthority - authetication에 부여된 권한
6. AuthenticationManager - authentication관련 filter작동시키는 manager
7. ProviderManager - authentication manager의 일반적인 구현체
8. AuthenticationProvider - ProviderManager에서 사용됨
9. Request Credentials with AuthenticationEntryPoint - 사용자에게 인증요구하는 동작을 쉽게 구현해줌(ex. 로그인 페이지로 ㅏ안내)
10. AbstractAuthenticationProcessingFilter - 인증에 사용되는 기본적인 필터

## 1. 소개

## 2. SecurityContextHolder
<img src="./img/securitycontextholder.png" />
`SecurityContextHolder`는 어떤 사용자가 인증되었는지 저장하는 객체이다.  
이 객체는 스프링 시큐리티의 몸통같은 존재이며(위의 그림처럼) 사용자단위로 작동하므로 
스프링 시큐리티는 사용자 단위로 인증을 구현했음을 알 수 있다.  
  
현재 사용자가 인증되었다는 정보를 추가하는 방법
```java
SecurityContext context = SecurityContextHolder.createEmptyContext(); 
Authentication authentication =
    new TestingAuthenticationToken("username", "password", "ROLE_USER"); 
context.setAuthentication(authentication);

SecurityContextHolder.setContext(context); 
```
현재 사용자에대한 정보를 불러오는 방법
```java
SecurityContext context = SecurityContextHolder.getContext();
Authentication authentication = context.getAuthentication();
String username = authentication.getName();
Object principal = authentication.getPrincipal();
Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
```
  
위의 예시처럼 context를 만들거나 조회하면 모두 현재 쓰레드에서 담당하는 사용자 관련 context로 처리함을 알 수 있다.  
  
__ThreadLocal 방식__
`SecurityContextHolder`는 [`ThreadLocal`](https://madplay.github.io/post/java-threadlocal)을 이용해 정보를 저장한다.  
이는 같은 쓰레드내에서는 `SecurityContext`를 전역변수처럼 어디에서나 접근가능하다는 얘기이다. 
안전하게 사용하기위해 finalization에 신경써야하는데 이는 `FilterChainProxy`에서 담당한다. 
ThreadLocal방식을 사용하지 않을 수 도 있는데 이는 공식사이트 [참고](https://docs.spring.io/spring-security/reference/servlet/authentication/architecture.html#servlet-authentication-securitycontextholder)
  
## 3. SecurityContext
현재 사용자에대한 security 정보를 담는다는 의미.  
`Authentication`객체를 포함한다.  

## 4. Authentication
`Authentication`객체는 다음 정보들을 포함한다.  
* `principal` - user에 대한 식별자. `UserDetails`를 많이 사용한다.
* `credentials` - 인증증명 정보. 주로 비밀번호(노출되지않도록 한다면)
* `authorities` - role이나 scope등인데 이것이 더 추상화된게 `GrantedAuthority`들을 사용하는것

*Authentication의 역할*
1. `AuthenticationManager`의 파라미터로 사용된다. 이를 통해 현재 사용자가 인증되었는지 아닌지등을 알 수 있다.
2. 현재 인증된 사용자의 정보를 포함한다.  

## 5. GrantedAuthority
사용자의 권한을 나타내는데 이는 높은 추상도를 가진객체이다.(기본적으로 role이나 scopes등 사용)  

특징들은 다음과 같다.  
* `Authentication.getAuthorities()` 메소드를 사용해 `GrantedAuthority`의 컬렉션 접근가능
* username/password 방식을 사용할 경우 주로 `UserDetailsService`가 `GrantedAuthority`를 포함한다.

*주의사항*  
GrantedAuthority는 특정 쓰레드 내에서만 작동되도록 하는것보다 애플리케이션에서 
특정타입의 유저를 분류해 타입의 전반적으로 적용시키는것이 낫다.  
  
## 6. AuthenticationManager
[Api Doc보기](https://docs.spring.io/spring-security/site/docs/5.6.3/api/org/springframework/security/authentication/AuthenticationManager.html)  
Spring Security의 Filter들이 어떻게 authentication과정을 해야하는지 정의해놓은 인터페이스이다.  
주로 ProviderManager를 사용한다.  
  
## 7. ProviderManager
*무슨 역할을 하는가?*  
`ProviderManager`는 `AuthenticationProvider`의 리스트를 받아 authentication을 수행해준다.  
  
__`AuthenticationProvider`의 리스트__  
각각의 provider는 인증을 성공, 실패, 결정불가로 리턴해준다. 
이것들의 리스트를 모아 ProviderManager에 전달하면 리스트의 객체들을 이용해 인증이 수행된다.  


## 8. AuthenticationProvider
인증을 해주는 객체. 여러개의 AuthenticationProvier가 ProviderManager에 적용된다.  
이때 각각의 `AuthenticationProvider`는 각자의 방식으로 인증을 진행한다. 예를 들면 username/password혹은 jwt  


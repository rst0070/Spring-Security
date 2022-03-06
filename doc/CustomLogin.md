# Form Based Authentication
1. client가 form을 통해서 id, password정보 전달
2. Server가 확인후 맞으면 session id 전송(쿠키)
3. 각 request에 session id를 포함하여 인증을 한다.

## Session ID의 저장
클라이언트에선 쿠키를 이용해 session id를 저장하고  
서버에선 다양한 방법으로 이를 저장한다.
* in memory
* database
* etc...  
__[Spring Session 소개](https://spring.io/projects/spring-session)__
## custom login page
`HttpSecurity.loginPage("로그인 페이지에 연결하는 url")`이것을 통해 지정한다. 
또한 이 뒤에 해당 경로의 permission지정해주면 됨.  
  
__default success url__  
로그인이 성공했을경우 redirect하는 url을 지정할 수 있다.  
login page를 지정한 후 `defaultSuccessUrl()`을 통해 지정한다.  

## remember me
`HttpSecurity.rememberMe()`를 이용하면 세션의 유효기간을 연장할 수 있다.  
이때 로그인 폼에 `name='remember-me'`인 checkbox를 추가해주면 된다.  
remember-me 옵션을 이용하면 로그인 요청에 대한 응답에 `remember-me`쿠키가 추가됨.
이 쿠키는 세션id처럼 서버의 데이터베이스에 저장된다. 따라서 이 쿠키의 값을 이용해 remember-me가 작동하는것.
```java
.rememberMe()
    //remember me 유지 시간
    .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))
    //정보를 저장하는 md5 hash의 키값
    .key("....")
    //데이터베이스 지정
    .tokenRepository()
```
remember-me 쿠키는 `username`, `expiration time`을 포함한다(md5 hash로 이 데이터를 저장).  

## Logout
아래와 같은 방식으로 설정이 가능하다.
```java
.and()
.logout()
.logoutUrl("/logout")
.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))
.clearAuthentication(true)
.invalidateHttpSession(true)
.deleteCookies()
.logoutSuccessUrl("/login");
```
이때 `csrf`보호 기능이 켜져있으면 로그아웃 요청은 `post`로만 가능하다.  
logout은 정해진 url로 요청을 보내기만 하면 됨.

## Parameter 변경하기
form의 username, password, remember-me등의 파라미터 이름을 변경할 수 있다.  
```java
.formLogin()
    ...
    .passwordParameter("mypassword")
    .usernameParameter("myusername")
    
.rememberMe()
    ...
    .rememberMeParameter("asdasdd")
```

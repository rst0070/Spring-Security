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



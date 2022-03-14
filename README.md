# Spring security 시작하기
게시글을 접근하고 수정하는 api를 만들어보고, 이 api에 spring security를 적용해보자.  
이 프로젝트는 유투버 [amigos code의 영상](https://youtu.be/her_7pa0vrg)을 보고 만들었다.  

1. 필요한 dependency
2. 게시글 api를 만들자

## 1. 필요한 dependency
spring security는 `spring-boot-starter-sequrity`만 프로젝트에 추가하면 바로 작동한다.
```xml
<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-security</artifactId>
</dependency>
```
## 2. 게시글 API를 만들자
게시글을 조회, 변경하는 api를 만들고 이에 대해 spring security를 적용해볼 것 이다.
* `com.example.demo.article.Article` - 게시글 객체 : `author`, `title`, `content`등의 데이터를 가진다.
* `com.example.demo.article.ArticleController` - 게시글에 대한 rest api를 제공하는 컨트롤러
* `com.example.demo.article.ArticleService` - 게시글 조회, 변경등을 가능하게 하는 서비스  
  
`client` <-> `controller` <-> `service` <-> `Article`방식으로 작동


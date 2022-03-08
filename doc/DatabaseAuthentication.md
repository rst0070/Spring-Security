# Database Authentication
외부 데이터베이스를 이용해서 사용자의 정보를 저장하고 유지하자.
이 튜토리얼에선 entity, service, dao를 이용해 사용자 데이터에 접근한다.  
  
* `ApplicationUser` : entity
* `ApplicationUserDao` : 데이터 접근을 구조화한 인터페이스
* `FakeApplicationUserDao` : 임시로 데이터를 제공하는 dao(직접 db접근 안함)
* `ApplicationUserService` : entity 접근등 비지니스 로직을 담은 서비스

## Application User Class
`UserDetails`를 구현하여 새로운 클래스를 만들자 `com.example.demo.auth.ApplicationUser`  

##  UserDetailsService 구현
유저의 정보를 제공하는 서비스를 만들자.

## DAO Authentication provider
  
이러한 구조를 통해 user의 정보를 db에 저장하는것.

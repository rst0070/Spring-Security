# Spring security
  
1. enabling spring sequrity
2. Configure Spring Sequrity

## 1. Enabling Spring Security
by adding `spring-boot-starter-sequrity` security added.  
  
__제공하는것이 무엇인가?(혹은 특징)__  
* require authentication for all http request.
* only one user(user name = "user")
* prompt http authentication  
  
__to do in this chapter__  
1. login page
2. sign in page
3. apply different sequrity rules for different request path.
  
## 2. Configure
basic configuration looks like..  
```java
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    ...
}

```
by override `WebSecurityConfigurerAdapter:configure`, you can customize user store.  
  
look at [documents](https://docs.spring.io/spring-security/reference/5.7/servlet/authentication/passwords/jdbc.html)
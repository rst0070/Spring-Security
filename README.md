# Json Web Token
__장점__  
* 빠름
* stateless함 (세션 저장 x)
* 다양한 서비스 형태에 이용가능
  
__단점__  
* secret key의 노출 위험도 상승
* user에 대해 로그인 처리를 하지않으므로 명확하게 구분 불가
* 토큰이 노출될 수 있음.

## 사용자 권한을 위한 JWT 통신 과정
1. client가 server에 (username, password)와 같은 credential 정보 보냄
2. server가 유효성을 확인하고 token response함
3. client의 모든 request에 token을 붙임

## JWT의 구조


### 1. validate credentials
username과 password의 유효성을 확인하는 로직 만들자.

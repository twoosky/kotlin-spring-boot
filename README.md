# kotlin-spring-boot
## 1. REST
* `인터페이스의 일관성`: 아키텍처를 단순화시키고 작은 단위로 분리하여서, 클라이언트, 서버가 독립적으로 개선될 수 있어야 한다.  
* 인터페이스 일관성이 잘 지켜졌는지에 따라 REST를 잘 사용했는지 판단할 수 있다.  

**REST Ful 조건**
1. 자원 식별
* 웹 기반의 REST에서는 리소스 접근을 URI를 사용한다.
* 리소스와 식별자가 잘 구분되어 있어야 한다.
* Resource: user, 식별자: 100
```
https://foo.co.kr/user/100
```
2. 메시지를 통한 리소스 조작
* Web에서는 다양한 방식으로 데이터를 전송할 수 있다. (HTML, XML, JSON, TEXT ..)
* HTTP기반의 REST에서는 리소스의 타입을 알려주기 위해 header의 content-type을 통해서 타입을 명시해줄 수 있다.

3. 자기서술적 메시지
* 요청 하는 데이터가 어떻게 처리 되어야 하는지 충분한 데이터를 포함해야 한다.
* HTTP기반의 REST에서는 `HTTP Method`와 `Header`의 정보로 이를 표현할 수 있다.

4. 애플리케이션 상태에 대한 엔진으로서 하이퍼미디어
* REST API를 개발할 때 단순히 Client 요청에 대한 데이터만 내리는 것이 아닌, 관련 리소스에 대한 `Link` 정보까지 같이 포함해야 한다.
* Spring에서는 `Hateoas` 라이브러리를 사용해 구현할 수 있다.

> 이러한 조건들을 잘 갖춘 경우 `REST Ful`하다고 하고, 이를 `REST API`라고 부른다.

## 2. URI 설계
* `URI`: 인터넷에서 특정 자원을 나타내는 주소값, 해당 값을 유일해야 한다.  
* `URL`: 인터넷 상에서의 자원, 특정파일이 어디에 위치하는지 식별하는 주소, URI의 하위 개념이다.  

**URI 설계 원칙**  
1. 슬래시 구분자 (/)는 계층 관계를 나타내는데 사용한다.
2. URI 마지막 문자로 (/)는 포함하지 않는다.
3. 하이폰(-)은 URI 가독성을 높이는데 사용한다.
4. 밑줄(_)은 사용하지 않는다.
5. URI 경로에는 소문자가 적합하다.
6. 파일 확장자는 URI에 포함하지 않는다.
7. 프로그래밍 언어에 의존적인 확장자를 사용하지 않는다.
8. 구현에 의존적인 경로를 사용하지 않는다.
9. 세션 ID를 포함하지 않는다.
```
https://foo.co.kr/vehicles/suv/q6?session-id=abcdef
```
10. 프로그래밍 언어의 Method명을 포함하지 않는다.
11. 명사에 단수형 보다는 복수형을 사용해야 한다. 컬렉션에 대한 표현은 복수로 사용
```
https://foo.co.kr/`vehicles`/suv/q6
```
12. 컨트롤러 이름으로는 동사나 동사구를 사용한다.
```
https://foo.co.kr/vehicles/suv/q6/`re-order`
```
13. URI 경로 중 변하는 부분은 유일한 값으로 대체할 수 있어야 한다. (PathVariable)
```
https://foo.co.kr/vehicles/suv/q7/`{car-id}`/users/{user-id}/release
https://foo.co.kr/vehicles/suv/q7/`117`/users/`100`/release
```
14. CRUD 기능을 나타내는 것은 URI에 사용하지 않는다. HTTP Mehod로 구분한다.
15. URI 쿼리 부분으로 컬렉션 결과에 대해서 필터링할 수 있다.
16. URI 쿼리는 컬렉션의 결과를 페이지로 구분하여 나타내는데 사용할 수 있다.

## 3. HTTP
* `HTTP`는 Web에서 데이터를 주고 받는 프로토콜이다. 다양한 형태의 데이터를 주고받을 수 있다.
* `HTTP`는 TCP를 기반으로한 REST의 특징을 모두 구현하고 있는 Web 기반의 프로토콜이다.

**HTTP Method**
||의미|CRUD|멱등성|안정성|PathVariable|Query Parameter|DataBody|
|---|---|---|---|---|---|---|---|
|GET|리소스 취득|R|O|O|O|O|X|
|POST|리소스 생성, 추가|C|X|X|O|△|O|
|PUT|리소스 갱신, 생성|C/U|O|X|O|△|O|
|DELETE|리소스 삭제|D|O|X|O|O|X|
|HEAD|헤더 데이터 취득|-|O|O|-|-|-|
|OPTIONS|지원하는 메소드 취득|-|O|-|-|-|-|
|TRACE|요청메시지 반환|-|O|-|-|-|-|
|CONNECT|프록시 동작의 터널 접속으로 변경|-|X|-|-|-|-|

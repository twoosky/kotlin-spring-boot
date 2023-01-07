package com.example.mvc.controller

import com.example.mvc.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class GetApiController {

    @GetMapping("/hello")
    fun hello(): String {
        return "hello kotlin"
    }

    @GetMapping(path = ["/hello", "/abcd"])
    fun hello2(): String {
        return "hello2 kotlin"
    }

    @RequestMapping(method = [RequestMethod.GET], value = ["/request-mapping"])
    fun requestMapping(): String {
        return "request-mapping"
    }

    @GetMapping("/get-mapping/path-variable/{name}/{age}")
    fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
        println("${name}, ${age}")
        return "${name}, ${age}"
    }

    // path의 PathVariable 변수명과 다른 변수명을 쓰는 경우
    // - PathVariable의 value, name 속성으로 path의 변수명을 명시해준다.
    @GetMapping("/get-mapping/path-variable2/{name}/{age}")
    fun pathVariable2(
        @PathVariable(value = "name") _name: String,
        @PathVariable(name = "age") _age: Int
    ): String {
        println("${_name}, ${_age}")
        return "${_name}, ${_age}"
    }

    // http://localhost:8080/api/get-mapping/query-param?page=1&size=10
    @GetMapping("/get-mapping/query-param")
    fun queryParam(
        @RequestParam(name = "page") _page: Int,
        @RequestParam(value = "size") _size: Int
    ): String {
        println("${_page}, ${_size}")
        return "${_page}, ${_size}"
    }

    // 객체를 반환하면 ObjectMapper에 의해 자동으로 JSON 형태로 변환되어 반환된다.
    @GetMapping("/get-mapping/query-param/object")
    fun queryParamObject(userRequest: UserRequest): UserRequest {
        println(userRequest)
        return userRequest
    }

    // 쿼리 파라미터를 Map으로 받기
    @GetMapping("/get-mapping/query-param/map")
    fun queryParamMap(@RequestParam map: Map<String, Any>): Map<String, Any> {
        println(map)
        return map
    }
}
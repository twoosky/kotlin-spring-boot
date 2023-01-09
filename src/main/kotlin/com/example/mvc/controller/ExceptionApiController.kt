package com.example.mvc.controller

import com.example.mvc.model.http.UserRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello(): String {
//        if (true) {
//            throw RuntimeException("강제 exception 발생")
//        }
        val list = mutableListOf<String>()
//        val temp = list[0]
        return "hello"
    }

    // 1. bean이 아닌 경우 validation API
    @GetMapping
    fun get(
        @NotBlank @Size(min = 2, max = 8) @RequestParam name: String,
        @Min(10) @RequestParam age: Int
    ): String {
        println(name)
        println(age)
        return "${name} ${age}"
    }

    // 2. bean인 경우 validation API
    @PostMapping
    fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest.toString())
        return userRequest
    }
}
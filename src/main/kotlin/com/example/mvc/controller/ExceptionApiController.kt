package com.example.mvc.controller

import com.example.mvc.model.http.UserRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api/exception")
// @Validated
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello() {
//        if (true) {
//            throw RuntimeException("강제 exception 발생")
//        }
        val list = mutableListOf<String>()
        val temp = list[0]
    }

    // 1. bean이 아닌 경우 validation API
    @GetMapping("/validation")
    fun get(
        @NotBlank @Size(min = 2, max = 8) @RequestParam name: String,
        @Min(10) @RequestParam age: Int
    ): String {
        println(name)
        println(age)
        return "${name} ${age}"
    }

    // 2. bean인 경우 validation API
    @GetMapping("/validation2")
    fun getBean(@Valid @RequestBody userRequest: UserRequest): UserRequest {
        println(userRequest.toString())
        return userRequest
    }
}
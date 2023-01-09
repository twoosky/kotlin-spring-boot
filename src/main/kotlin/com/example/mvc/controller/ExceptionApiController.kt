package com.example.mvc.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/exception")
class ExceptionApiController {

    @GetMapping("/hello")
    fun hello() {
//        if (true) {
//            throw RuntimeException("강제 exception 발생")
//        }
        val list = mutableListOf<String>()
        val temp = list[0]
    }
}
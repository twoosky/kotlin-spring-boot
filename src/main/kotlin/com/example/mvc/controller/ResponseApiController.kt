package com.example.mvc.controller

import com.example.mvc.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

    // RequestParam 의 required 속성을 통해 해당 파라미터 필수 여부를 지정할 수 있다. default true(필수)
    @GetMapping
    fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {
        // 표준함수를 사용해 코틀린스럽게 작성해보자
        return age?.let {
            // age not null
            if (it < 20) {
                return ResponseEntity.status(400).body("fail")
            }
            ResponseEntity.ok("OK")
        }?: kotlin.run {
            // age is null
            return ResponseEntity.status(400).body("fail")
        }

        /*
        if (age == null) {
            return ResponseEntity.status(400).body("fail")
        }

        if (age < 20) {
            return ResponseEntity.status(400).body("fail")
        }
        return ResponseEntity.ok("OK")
        */
    }

    @PostMapping
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(200).body(userRequest)
    }

    @PutMapping
    fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
    }

    @DeleteMapping("/{id}")
    fun deleteMapping(@PathVariable id: Int): ResponseEntity<String> {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null)
    }
}
package com.example.mvc.controller

import com.example.mvc.model.http.UserRequest
import org.jetbrains.annotations.NotNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.Min

@RestController
@RequestMapping("/api/response")
//@Validated
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

    // 1. bean이 아닌 곳에 validation 적용하기
    // - age 파라미터는 bean이 아니기 때문에 클래스에 @Validated 를 붙여줘야 한다.
    @GetMapping("/validation")
    fun getMappingValidated(
        @RequestParam name: String?,

        @NotNull
        @Min(value = 20, message = "age는 20보다 커야 합니다.")
        @RequestParam age: Int
    ): ResponseEntity<String> {
        println(name)
        println(age)
        return ResponseEntity.status(200).body("${name}, ${age}")
    }

    @PostMapping
    fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(200).body(userRequest)
    }

    // 2. bean에 대해 validation 적용하기
    @PostMapping("/validation")
    fun postMappingValidation(@Valid @RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
        return ResponseEntity.status(200).body(userRequest)
    }

    // 3. validation 결과를 bindingResult 에 담아 에러 핸들링하기
    // - 주의: @Validated 어노테이션 사용 시 bindingResult 에 validation 결과가 담기지 않는다.
    @PostMapping("/validation2")
    fun postMappingValidation2(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult): ResponseEntity<String> {
        if (bindingResult.hasErrors()) {
            val message = StringBuilder()
            bindingResult.allErrors.forEach {
                val field = it as FieldError
                val msg = it.defaultMessage
                println("${field.field} : ${msg}")
                message.append("${field.field} : ${msg}\n")
            }
            println(message.toString())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message.toString())
        }
        return ResponseEntity.status(200).body("OK")
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
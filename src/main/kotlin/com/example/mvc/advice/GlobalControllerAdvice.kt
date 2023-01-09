package com.example.mvc.advice

import com.example.mvc.controller.PutApiController
import com.example.mvc.controller.ResponseApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// controller 에 대해 전역적으로 발생할 수 있는 예외를 잡아서 처리하기 위한 어노테이션
// - 응답으로 객체를 리턴하는 경우 @RestControllerAdvice 사용
// - basePackageClasses 속성을 사용해 예외 처리 컨트롤러 지정 가능
@RestControllerAdvice
class GlobalControllerAdvice {

    // 예외 처리 클래스를 지정하는 어노테이션
    // - AOP 를 이용한 예외처리 방식
    @ExceptionHandler(value = [RuntimeException::class])
    fun exception(e: RuntimeException): String {
        return "Server Error"
    }

    @ExceptionHandler(value = [IndexOutOfBoundsException::class])
    fun indexOutOfBoundsException(e: IndexOutOfBoundsException): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Index Error")
    }
}
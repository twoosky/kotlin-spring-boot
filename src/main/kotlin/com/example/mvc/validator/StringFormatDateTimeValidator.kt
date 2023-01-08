package com.example.mvc.validator

import com.example.mvc.annotation.StringFormatDateTime
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

// 어노테이션을 통해 검증할 실제 validator 클래스
class StringFormatDateTimeValidator : ConstraintValidator<StringFormatDateTime, String> {

    private var pattern: String? = null

    // 어노테이션에 지정한 pattern 가져오는 메서드
    override fun initialize(constraintAnnotation: StringFormatDateTime?) {
        this.pattern = constraintAnnotation?.pattern
    }

    // 실제 검증 메서드 (정상이면 true, 비정상이면 false)
    // - value 인자에는 검증할 값이 들어온다.
    override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
        return try {
            LocalDateTime.parse(value, DateTimeFormatter.ofPattern(pattern))
            true
        } catch (e: Exception) {
            false
        }
    }

}
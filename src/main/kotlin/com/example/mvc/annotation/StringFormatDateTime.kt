package com.example.mvc.annotation

import com.example.mvc.validator.StringFormatDateTimeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

// 실제로 값을 검증하는 객체(validator) 지정
@Constraint(validatedBy = [StringFormatDateTimeValidator::class])
// 어노테이션 적용 범위 설정
@Target(
    AnnotationTarget.FIELD,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
// 어노테이션 적용 시점 설정
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class StringFormatDateTime(
    val pattern: String = "yyyy-MM-dd HH:mm:ss",
    val message: String = "시간 형식이 유효하지 않습니다",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)
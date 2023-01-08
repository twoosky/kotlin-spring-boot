package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.validation.constraints.*

// dto 자체에 JSON 변수명 규칙 지정
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest (

    // kotlin에서 변수에 validation 관련 어노테이션 사용 시 @field 를 꼭 붙여줘야 한다.
    @field:NotEmpty
    @field:Size(min = 2, max = 8)
    var name: String? = null,

    @field:PositiveOrZero  // 0보다 큰 수 즉, 양수인지 검증
    var age: Int? = null,

    @field:Email
    var email: String? = null,

    @field:NotBlank
    var address: String? = null,

    // JSON 변수명 지정 어노테이션
    @JsonProperty("phone_number")
    @field:Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}\$")
    var phoneNumber: String? = null,

    var createdAt: String? = null  // yyyy-MM-dd HH:mm:ss
){
    // 검증 과정에서 메소드가 실행될 수 있도록 해주는 어노테이션
    @AssertTrue(message = "생성일자의 패턴은 yyyy-MM-dd HH:mm:ss 여야 합니다.")
    private fun isValidCreatedAt(): Boolean {
        return try {
            LocalDateTime.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            true
        } catch (e: Exception) {
            false
        }
    }
}
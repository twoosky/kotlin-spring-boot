package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty

// dto 자체에 JSON 변수명 규칙 지정
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
data class UserRequest (
    var name: String? = null,
    var age: Int? = null,
    var email: String? = null,
    var address: String? = null,

    // JSON 변수명 지정 어노테이션
    @JsonProperty("phone_number")
    var phoneNumber: String? = null
)
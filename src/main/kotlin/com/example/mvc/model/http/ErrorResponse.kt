package com.example.mvc.model.http

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ErrorResponse(
    // data class 에서는 어노테이션 앞에 field 붙여야됨
    @field:JsonProperty("result_code")
    var resultCode: String? = null,
    @field:JsonProperty("http_status")
    var httpStatus: String? = null,
    @field:JsonProperty("http_method")
    var httpMethod: String? = null,
    var message: String? = null,
    var path: String? = null,
    var timestamp: LocalDateTime? = null,
    var errors: MutableList<Error>? = mutableListOf()
)

data class Error(
    var field: String? = null,
    var message: String? = null,
    var value: Any? = null
)

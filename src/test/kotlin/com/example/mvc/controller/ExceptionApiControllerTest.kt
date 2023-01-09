package com.example.mvc.controller

import com.example.mvc.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception/hello")
        ).andExpect (
            MockMvcResultMatchers.status().isOk
        ).andExpect (
            MockMvcResultMatchers.content().string("hello")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "sky")
        queryParams.add("age", "24")

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception")
                .queryParams(queryParams)
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.content().string("sky 24")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun getFailTest() {
        val queryParams = LinkedMultiValueMap<String, String>()
        queryParams.add("name", "sky")
        queryParams.add("age", "0")

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/exception")
                .queryParams(queryParams)
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest
        ).andExpect(
            MockMvcResultMatchers.content().contentType("application/json")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.result_code").value("FAIL")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.errors[0].field").value("age")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.errors[0].value").value("0")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun postTest() {
        val userRequest = UserRequest().apply {
            this.name = "sky"
            this.age = 24
            this.phoneNumber = "010-1111-2222"
            this.address = "서울 특별시"
            this.email = "sky@gmail.com"
            this.createdAt = "2023-01-09 20:12:00"
        }

        val requestJson = jacksonObjectMapper().writeValueAsString(userRequest)
        println(requestJson)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/exception")
                .content(requestJson)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            MockMvcResultMatchers.status().isOk
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.name").value("sky")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.address").value("서울 특별시")
        ).andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun postFailTest() {
        val userRequest = UserRequest().apply {
            this.name = "sky"
            this.age = 24
            this.phoneNumber = "010-d1111-2222"
            this.address = "서울 특별시"
            this.email = "sky.gmail.com"
            this.createdAt = "11112023-01-09 20:12:00"
        }

        val requestJson = jacksonObjectMapper().writeValueAsString(userRequest)
        println(requestJson)

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/exception")
                .content(requestJson)
                .contentType("application/json")
                .accept("application/json")
        ).andExpect(
            MockMvcResultMatchers.status().isBadRequest
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.result_code").value("FAIL")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.errors[0].field").value("createdAt")
        ).andExpect(
            MockMvcResultMatchers.jsonPath("\$.errors[0].value").value("11112023-01-09 20:12:00")
        ).andDo(MockMvcResultHandlers.print())
    }
}

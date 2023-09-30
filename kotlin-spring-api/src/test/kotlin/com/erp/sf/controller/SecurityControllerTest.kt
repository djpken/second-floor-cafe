package com.erp.sf.controller

import com.erp.sf.JunitController
import com.erp.sf.JunitService
import com.erp.sf.constant.PATH
import com.erp.sf.controller.security.SecurityController
import com.erp.sf.entity.SysUser
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext


class SecurityControllerTest : JunitController() {
    @Autowired
    lateinit var mvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper
    lateinit var uri: String
    lateinit var securityController: SecurityController

    @BeforeEach
    override fun beforeEach() {
    }

    @Test
    fun login() {
        val sysUser: SysUser = SysUser(null, "003958", "003958")
        uri = PATH.SECURITY + SecurityController.POST_LOGIN
        val result =
            mvc.perform(
                MockMvcRequestBuilders.post(uri).accept(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(sysUser))
            ).andReturn()
        println(result.response.status)
    }
}
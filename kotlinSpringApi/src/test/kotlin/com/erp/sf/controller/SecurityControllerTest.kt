package com.erp.sf.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc


@WebMvcTest
class SecurityControllerTest {
    @Autowired
    private lateinit var mvc: MockMvc
}
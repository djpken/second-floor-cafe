package com.erp.sf.handler.impl

import cn.hutool.http.HttpStatus
import cn.hutool.json.JSONUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class AuthenticationEntryPointImpl :AuthenticationEntryPoint{
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        val map: Map<String, String> = mapOf("message" to "HTTP_UNAUTHORIZED")
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        response.writer.write(JSONUtil.toJsonStr(map))
        response.status=HttpStatus.HTTP_UNAUTHORIZED
    }
}
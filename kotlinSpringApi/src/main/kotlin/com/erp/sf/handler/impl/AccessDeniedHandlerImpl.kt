package com.erp.sf.handler.impl

import cn.hutool.http.HttpStatus
import cn.hutool.json.JSONUtil
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.access.AccessDeniedException
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

@Component
class AccessDeniedHandlerImpl : AccessDeniedHandler {
    override fun handle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        accessDeniedException: AccessDeniedException
    ) {
        val map:Map<String ,String> = mapOf("message" to "HTTP_FORBIDDEN")
        response.contentType = "application/json"
        response.characterEncoding = "UTF-8"
        response.writer.write(JSONUtil.toJsonStr(map))
        response.status=HttpStatus.HTTP_FORBIDDEN;
    }
}
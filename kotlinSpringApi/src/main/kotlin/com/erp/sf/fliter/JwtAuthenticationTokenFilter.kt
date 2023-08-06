package com.erp.sf.fliter

import cn.hutool.jwt.JWTUtil
import com.erp.sf.util.RedisUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.lang.Exception
import java.lang.RuntimeException
import java.util.*

@Component
class JwtAuthenticationTokenFilter :OncePerRequestFilter() {
    @Autowired
    private lateinit var redisUtil: RedisUtil;

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token= request.getHeader("Authorization") ?: throw RuntimeException("token is null")
        val subString = token.substring(7)
        try {
            val parseToken = JWTUtil.parseToken(subString)
            parseToken.getPayload("id").toString();
        }catch (e:Exception){
            throw  RuntimeException("token is error")
        }

    }
}
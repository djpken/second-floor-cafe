package com.erp.sf.fliter

import cn.hutool.json.JSONUtil
import cn.hutool.jwt.JWTUtil
import com.erp.sf.entity.SysUser
import com.erp.sf.exception.TokenValidationException
import com.erp.sf.model.LoginUser
import com.erp.sf.model.SysResponse
import com.erp.sf.component.RedisComponent
import com.erp.sf.constant.M
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationTokenFilter : OncePerRequestFilter() {
    @Autowired
    private lateinit var redisUtil: RedisComponent;

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization") ?: ""
        if (token.isBlank()) {
            filterChain.doFilter(request, response)
            return
        }
        val userId: Long;
        try {
            val parseToken = JWTUtil.parseToken(token.substring(7))
            userId = parseToken.getPayload("id").toString().toLong()
        } catch (e: Exception) {
            throw TokenValidationException(SysResponse(M.TOKEN_FAILED))
        }
        val redisKey = "login:$userId"
        val list: List<String> = JSONUtil.toList(JSONUtil.parseArray(redisUtil[redisKey]), String::class.java)
            ?: throw TokenValidationException(SysResponse(M.TOKEN_FAILED))
        val loginUser = LoginUser(SysUser(userId), list)
        val authentication = UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities())
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }
}
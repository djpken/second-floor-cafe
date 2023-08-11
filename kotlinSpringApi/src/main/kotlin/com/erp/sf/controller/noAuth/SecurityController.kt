package com.erp.sf.controller.noAuth

import com.erp.sf.constant.C
import com.erp.sf.constant.M
import com.erp.sf.model.ApiResponse
import com.erp.sf.entity.SysUser
import com.erp.sf.service.security.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/security")
class SecurityController {
    @Autowired
    private lateinit var loginService: LoginService

    @PostMapping("/login")
    fun login(@RequestBody sysUser: SysUser): ResponseEntity<ApiResponse<Any>> {
        val map = loginService.login(sysUser) ?: return ResponseEntity.badRequest().body(
            ApiResponse(
                C.BUSINESS_FAILED,
                M.LOGIN_FAILED
            )
        )
        return ResponseEntity.ok(ApiResponse(map))
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<ApiResponse<Any>> {
        val map = loginService.logout()
        if (map == emptyMap<String, Any>()) {
            return ResponseEntity.badRequest().body(ApiResponse(C.BUSINESS_FAILED, M.LOGOUT_FAILED))
        }
        return ResponseEntity.ok(ApiResponse(map))
    }

    @PostMapping("/register")
    fun register(@RequestBody sysUser: SysUser): ResponseEntity<ApiResponse<Any>> {
        val map = loginService.register(sysUser) ?: return ResponseEntity.badRequest()
            .body(ApiResponse(C.BUSINESS_FAILED, M.REGISTER_FAILED))
        return ResponseEntity.ok(ApiResponse(map))
    }
}
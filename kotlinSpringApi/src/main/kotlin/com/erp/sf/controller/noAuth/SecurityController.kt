package com.erp.sf.controller.noAuth

import com.erp.sf.entity.SysUser
import com.erp.sf.model.ResponseEntity
import com.erp.sf.model.TokenResponse
import com.erp.sf.service.security.LoginService
import org.springframework.beans.factory.annotation.Autowired
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
    fun login(@RequestBody sysUser: SysUser): ResponseEntity<TokenResponse> {
        val tokenResponse = loginService.login(sysUser)
        return ResponseEntity.ok(tokenResponse)
    }

    @PostMapping("/logout")
    fun logout(): ResponseEntity<TokenResponse> {
        val tokenResponse = loginService.logout()
        return ResponseEntity.ok(tokenResponse)
    }

    @PostMapping("/register")
    fun register(@RequestBody sysUser: SysUser): ResponseEntity<TokenResponse> {
        val tokenResponse =
            loginService.register(sysUser)
        return ResponseEntity.ok(tokenResponse)
    }
}
package com.erp.sf.controller.security

import com.erp.sf.constant.PATH
import com.erp.sf.entity.SysUser
import com.erp.sf.model.ResponseEntity
import com.erp.sf.model.responseEntity.apiResposne.TokenModel
import com.erp.sf.service.security.LoginService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(PATH.SECURITY)
class SecurityController {
    companion object {
        const val POST_LOGIN = "/login"
        const val POST_LOGOUT = "/logout"
        const val POST_REGISTER = "/register"
    }

    @Autowired
    private lateinit var loginService: LoginService

    @PostMapping(POST_LOGIN)
    fun login(@RequestBody sysUser: SysUser): ResponseEntity<TokenModel> {
        val tokenResponse = loginService.login(sysUser)
        return ResponseEntity.ok(tokenResponse)
    }

    @PostMapping(POST_LOGOUT)
    fun logout(): ResponseEntity<TokenModel> {
        val tokenResponse = loginService.logout()
        return ResponseEntity.ok(tokenResponse)
    }

    @PostMapping(POST_REGISTER)
    fun register(@RequestBody sysUser: SysUser): ResponseEntity<TokenModel> {
        val tokenResponse =
            loginService.register(sysUser)
        return ResponseEntity.ok(tokenResponse)
    }
}
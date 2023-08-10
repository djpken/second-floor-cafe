package com.erp.sf.controller.noAuth

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
    fun login(@RequestBody sysUser: SysUser):ResponseEntity<ApiResponse<Any>>{
        val map = loginService.login(sysUser) ?: return ResponseEntity.badRequest().body(ApiResponse.businessFailed("登入失敗"))
        return ResponseEntity.ok(ApiResponse.success(map,""))
    }
    @PostMapping("/logout")
    fun logout():ResponseEntity<ApiResponse<Any>>{
        return ResponseEntity.ok(ApiResponse.success(HashMap<String , Any>(), loginService.logout()["message"].toString()))
    }
    @PostMapping("/register")
    fun register(@RequestBody sysUser: SysUser):ResponseEntity<ApiResponse<Any>>{
        val register = loginService.register(sysUser)?:return ResponseEntity.badRequest().body(ApiResponse.businessFailed("註冊失敗"))
        return ResponseEntity.ok(ApiResponse.success(register,""))
    }
}
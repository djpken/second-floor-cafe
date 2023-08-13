package com.erp.sf.service.security

import com.erp.sf.entity.SysUser
import com.erp.sf.model.TokenResponse

interface LoginService{
    fun login(sysUser: SysUser): TokenResponse
    fun logout() : TokenResponse
    fun register(sysUser: SysUser): TokenResponse

}
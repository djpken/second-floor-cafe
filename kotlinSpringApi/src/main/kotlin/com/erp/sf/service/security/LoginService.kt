package com.erp.sf.service.security

import com.erp.sf.entity.SysUser

interface LoginService{
    fun login(employee: SysUser):Map<String, Any>
    fun logout() :Map<String, Any>
    fun register(employee: SysUser):Map<String, Any>

}
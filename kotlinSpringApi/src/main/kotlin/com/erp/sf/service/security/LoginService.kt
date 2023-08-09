package com.erp.sf.service.security

import com.erp.sf.entity.SysUser

interface LoginService{
    fun login(sysUser: SysUser):Map<String, Any>
    fun logout() :Map<String, Any>
    fun register(sysUser: SysUser):Map<String, Any>

}
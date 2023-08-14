package com.erp.sf.service.security

import com.erp.sf.entity.SysUser
import com.erp.sf.model.responseEntity.apiResposne.TokenModel

interface LoginService{
    fun login(sysUser: SysUser): TokenModel
    fun logout() : TokenModel
    fun register(sysUser: SysUser): TokenModel

}
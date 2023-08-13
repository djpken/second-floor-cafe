package com.erp.sf.model

import com.erp.sf.entity.SysUser
import java.io.Serializable


data class TokenResponse(val authorization: String, val authority:String, val sysUser: SysUser) : Serializable

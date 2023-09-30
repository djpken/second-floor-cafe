package com.erp.sf.model.responseEntity.apiResposne

import com.erp.sf.entity.SysUser
import java.io.Serializable


data class TokenModel(val authorization: String, val authority:String, val sysUser: SysUser) : Serializable

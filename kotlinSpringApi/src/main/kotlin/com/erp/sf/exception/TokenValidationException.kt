package com.erp.sf.exception

import com.erp.sf.model.sys.SysResponse

class TokenValidationException (response: SysResponse<Any>) : RuntimeException(response.message.message){
}
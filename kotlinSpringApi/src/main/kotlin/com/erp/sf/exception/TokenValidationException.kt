package com.erp.sf.exception

import com.erp.sf.model.SysResponse

class TokenValidationException (response: SysResponse<Any>) : RuntimeException(response.message.message){
}
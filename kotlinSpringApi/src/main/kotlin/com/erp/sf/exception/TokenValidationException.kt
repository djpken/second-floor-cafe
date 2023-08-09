package com.erp.sf.exception

import com.erp.sf.model.SystemResponse

class TokenValidationException (response: SystemResponse) : RuntimeException(response.message){
}
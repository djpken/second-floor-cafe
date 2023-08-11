package com.erp.sf.exception

import com.erp.sf.model.SysResponse

class DataException (response : SysResponse<Any>) : RuntimeException(response.message.message){
}
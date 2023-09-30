package com.erp.sf.exception

import com.erp.sf.model.sys.SysResponse

class DataException (response : SysResponse<Any>) : RuntimeException(response.message.message){
}
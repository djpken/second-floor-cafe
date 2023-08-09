package com.erp.sf.exception

import com.erp.sf.model.SystemResponse

class DataException (response : SystemResponse) : RuntimeException(response.message){
}
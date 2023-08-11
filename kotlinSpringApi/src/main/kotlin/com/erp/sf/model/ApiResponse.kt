package com.erp.sf.model

import com.erp.sf.constant.C
import com.erp.sf.constant.M
import java.io.Serializable


data class ApiResponse<T>(val code: Int, val data: T?, val message: String) : Serializable {
    //Fail
    constructor(code: C, data: T, message: M) : this(code.code, data, message.message)
    constructor(code: C, message: M) : this(code.code, null, message.message)

    //Success
    constructor(data: T, message: M) : this(C.SUCCESS.code, data, message.message)
    constructor(data: T) : this(C.SUCCESS.code, data, "")
}

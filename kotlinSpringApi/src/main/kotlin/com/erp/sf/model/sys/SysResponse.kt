package com.erp.sf.model.sys

import com.erp.sf.constant.M
import java.io.Serializable

data class SysResponse<T>(val data: T?, val message: M) : Serializable {
    constructor(message: M) : this(null, message)
}

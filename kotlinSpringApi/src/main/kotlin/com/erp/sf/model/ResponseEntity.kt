package com.erp.sf.model

import com.erp.sf.constant.C
import com.erp.sf.constant.M
import com.erp.sf.model.responseEntity.ApiResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap

data class ResponseEntity<T>(
    val httpStatus: HttpStatus,
    val data: ApiResponse<T>,
    val headers: MultiValueMap<String, String>?
) : ResponseEntity<ApiResponse<T>>(data, headers, httpStatus) {
    constructor(httpStatus: HttpStatus, data: ApiResponse<T>) : this(httpStatus, data, null)

    companion object {
        fun <T> ok(data: T): com.erp.sf.model.ResponseEntity<T> {
            return ResponseEntity(HttpStatus.OK, ApiResponse(data))
        }

        fun <T> ok(data: T, message: M): com.erp.sf.model.ResponseEntity<T> {
            return ResponseEntity(HttpStatus.OK, ApiResponse(data, message))
        }

        fun <T> badRequest(code: C, data: T, message: M): com.erp.sf.model.ResponseEntity<T> {
            return ResponseEntity(HttpStatus.BAD_REQUEST, ApiResponse(code, data, message))
        }

        fun <T> badRequest(code: C, message: M): com.erp.sf.model.ResponseEntity<T> {
            return ResponseEntity(HttpStatus.BAD_REQUEST, ApiResponse(code, message))
        }
    }
}

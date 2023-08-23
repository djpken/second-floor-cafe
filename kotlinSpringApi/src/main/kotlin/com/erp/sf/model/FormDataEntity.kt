package com.erp.sf.model

import com.erp.sf.constant.C
import com.erp.sf.constant.M
import com.erp.sf.model.responseEntity.ApiResponse
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.util.MultiValueMap

data class FormDataEntity<T>(
    val httpStatus: HttpStatus,
    val data: T,
    val headers: MultiValueMap<String, String>?
) : ResponseEntity<T>(data, headers, httpStatus) {
    companion object {
        fun <T> ok(data: T): FormDataEntity<T> {
            val parseMediaType = MediaType.parseMediaType(MediaType.MULTIPART_FORM_DATA.toString())
            val httpHeaders = HttpHeaders()
            httpHeaders.contentType = parseMediaType
            return FormDataEntity(HttpStatus.OK, data, httpHeaders)
        }
    }
}

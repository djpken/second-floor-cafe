package com.erp.sf.constant

//Code ID
enum class C(val code: Int) {
    SUCCESS(200),
    VALIDATE_FAILED(400),
    TOKEN_FAILED(401),
    BUSINESS_FAILED(501),
    DATA_FAILED(502),
}
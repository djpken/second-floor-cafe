package com.erp.sf.model.json

import org.springframework.web.multipart.MultipartFile

data class PhotoFile(
    val userId: Long?, val multipartFile: MultipartFile?
)

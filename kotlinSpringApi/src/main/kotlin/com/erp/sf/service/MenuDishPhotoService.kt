package com.erp.sf.service

import com.erp.sf.model.json.PhotoFile
import org.springframework.web.multipart.MultipartFile

interface MenuDishPhotoService {
    fun selectMenuDishPhotoByListMenuDishId(number: Int): List<MultipartFile>
    fun insertMapMenuDishPhoto(photoArray: List<PhotoFile>): Boolean
}

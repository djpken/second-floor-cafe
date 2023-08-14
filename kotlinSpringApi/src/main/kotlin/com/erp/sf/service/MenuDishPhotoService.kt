package com.erp.sf.service

import com.erp.sf.entity.MenuDishPhoto
import com.erp.sf.entity.MenuDishText
import org.springframework.web.multipart.MultipartFile
import java.io.File
import java.nio.file.Files

interface MenuDishPhotoService {
    fun selectMenuDishPhotoByMenuDishId(menuDishId: Long): MultipartFile
    fun selectMenuDishPhotoByListMenuDishId(menuDishId: List<Long>): List<MultipartFile>
    fun deleteMenuDishPhotoByMenuDishId(menuDishId: Long): Boolean
    fun deleteMenuDishPhotoByListMenuDishId(menuDishId: List<Long>): Boolean
    fun insertMenuDishPhotoByFile(multipartFile: MultipartFile): Boolean
    fun insertMapMenuDishPhoto(listMenuDishPhoto: List<MenuDishPhoto>) :Boolean
}

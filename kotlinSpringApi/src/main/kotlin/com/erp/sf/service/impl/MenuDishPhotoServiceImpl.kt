package com.erp.sf.service.impl

import com.erp.sf.component.ObjectByteComponent
import com.erp.sf.entity.MenuDishPhoto
import com.erp.sf.mapper.MenuDishPhotoMapper
import com.erp.sf.service.MenuDishPhotoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class MenuDishPhotoServiceImpl : MenuDishPhotoService {
    @Autowired
    private lateinit var menuDishPhotoMapper: MenuDishPhotoMapper

    @Autowired
    private lateinit var objectByteComponent: ObjectByteComponent

    override fun insertMenuDishPhotoByFile(multipartFile: MultipartFile): Boolean {
        try {
            val photoData = multipartFile.bytes
            val menuDishPhoto = MenuDishPhoto(data = photoData)
            menuDishPhotoMapper.insert(menuDishPhoto)
            return true
        } catch (e: IOException) {
            e.printStackTrace()
            return false
        }
    }
    override fun selectMenuDishPhotoByMenuDishId(menuDishId: Long): MultipartFile {
        val byteArrayOf = byteArrayOf(123)
        val byteToObject = objectByteComponent.byteToObject<MultipartFile>(byteArrayOf)
        return byteToObject
    }

    override fun selectMenuDishPhotoByListMenuDishId(menuDishId: List<Long>): List<MultipartFile> {
        return emptyList()
    }

    override fun deleteMenuDishPhotoByMenuDishId(menuDishId: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteMenuDishPhotoByListMenuDishId(menuDishId: List<Long>): Boolean {
        TODO("Not yet implemented")
    }

    override fun insertMapMenuDishPhoto(listMenuDishPhoto: List<MenuDishPhoto>): Boolean {
        try {
            menuDishPhotoMapper.insertBatch(listMenuDishPhoto)
        } catch (e: Exception) {
            return false
        }
        return true
    }
}
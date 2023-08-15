package com.erp.sf.service.impl

import com.erp.sf.component.ObjectByteComponent
import com.erp.sf.mapper.MenuDishPhotoMapper
import com.erp.sf.model.json.PhotoFile
import com.erp.sf.service.MenuDishPhotoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class MenuDishPhotoServiceImpl : MenuDishPhotoService {
    @Autowired
    private lateinit var menuDishPhotoMapper: MenuDishPhotoMapper

    @Autowired
    private lateinit var objectByteComponent: ObjectByteComponent
    override fun selectMenuDishPhotoByListMenuDishId(number: Int): List<MultipartFile> {
    }

    override fun insertMapMenuDishPhoto(photoArray: List<PhotoFile>): Boolean {
        photoArray.map {
        }
        menuDishPhotoMapper.insertBatch()
    }
}
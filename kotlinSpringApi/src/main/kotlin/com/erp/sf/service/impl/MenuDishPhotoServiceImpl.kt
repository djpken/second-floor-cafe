package com.erp.sf.service.impl

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl
import com.erp.sf.component.ObjectByteComponent
import com.erp.sf.entity.MenuDishPhoto
import com.erp.sf.mapper.MenuDishPhotoMapper
import com.erp.sf.service.MenuDishPhotoService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MenuDishPhotoServiceImpl : ServiceImpl<MenuDishPhotoMapper, MenuDishPhoto>(),MenuDishPhotoService {
    @Autowired
    private lateinit var menuDishPhotoMapper: MenuDishPhotoMapper

    @Autowired
    private lateinit var objectByteComponent: ObjectByteComponent
    override fun selectMenuDishPhotoBySeason(season: Int): List<MenuDishPhoto> {
        return menuDishPhotoMapper.selectMenuDishPhotoBySeason(season)
    }
}
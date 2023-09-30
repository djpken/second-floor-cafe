package com.erp.sf.service

import com.baomidou.mybatisplus.extension.service.IService
import com.erp.sf.entity.MenuDishPhoto

interface MenuDishPhotoService:IService<MenuDishPhoto> {
    fun selectMenuDishPhotoById(list: List<Long>): List<MenuDishPhoto>
}

package com.erp.sf.mapper

import com.erp.sf.entity.MenuDishPhoto
import com.erp.sf.mapper.aBase.MyBaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MenuDishPhotoMapper : MyBaseMapper<MenuDishPhoto> {
    fun selectMenuDishPhotoBySeason(season: Int) : List<MenuDishPhoto>
}
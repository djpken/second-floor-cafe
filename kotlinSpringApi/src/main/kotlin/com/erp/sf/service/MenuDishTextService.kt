package com.erp.sf.service

import com.baomidou.mybatisplus.extension.service.IService
import com.erp.sf.entity.MenuDishText

interface MenuDishTextService : IService<MenuDishText> {
    fun selectAllMenuDishText(): List<MenuDishText>
    fun selectMenuDishTextBySeason(season: Int): List<MenuDishText>
    fun insertListMenuDishText(list: List<MenuDishText>): List<MenuDishText>
}
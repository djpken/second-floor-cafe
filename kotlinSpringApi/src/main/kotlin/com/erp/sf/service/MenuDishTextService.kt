package com.erp.sf.service

import com.erp.sf.entity.MenuDishText

interface MenuDishTextService  {
    fun selectAllMenuDishText(): List<MenuDishText>
    fun selectMenuDishTextBySeason(season: Long): List<MenuDishText>
    fun insertListMenuDishText(list: List<MenuDishText>): List<MenuDishText>
}
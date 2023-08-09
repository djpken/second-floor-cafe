package com.erp.sf.service

import com.erp.sf.entity.MenuDish

interface MenuDishService  {
    fun selectAllMenuDish(): List<MenuDish>
    fun selectMenuDishBySeason(season: Long): List<MenuDish>
}
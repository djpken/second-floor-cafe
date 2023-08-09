package com.erp.sf.service

import com.erp.sf.entity.MenuDish

interface MenuDishService  {
    fun selectAllDish(): List<MenuDish>

}
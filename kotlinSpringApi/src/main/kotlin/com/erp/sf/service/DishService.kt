package com.erp.sf.service

import com.erp.sf.entity.MenuDish

interface DishService  {
    fun selectAllDish(): List<MenuDish>
    fun selectAllDishWithVisible(): List<MenuDish>
    fun insertDish(dish: MenuDish): Boolean
    fun deleteDish(dish: MenuDish): Boolean
    fun updateDish(dish: MenuDish): Boolean

}
package com.erp.sf.entity.impl

import com.erp.sf.entity.MenuDish
import com.erp.sf.mapper.MenuDishMapper
import java.util.*

class MenuDishTest(dao:MenuDishMapper,number: Int) : BaseTestEntity<MenuDish, MenuDishMapper>(dao,number) {
    override fun toList(number: Int) {
        for (i in 0 until number) {
            val s = UUID.randomUUID().toString()
            val menuDish = MenuDish(0, s, s)
            dao.insert(menuDish)
        }
    }
}
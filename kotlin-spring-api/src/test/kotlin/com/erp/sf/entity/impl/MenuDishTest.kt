package com.erp.sf.entity.impl

import com.erp.sf.entity.MenuDishText
import com.erp.sf.mapper.MenuDishTextMapper
import java.util.*

class MenuDishTest(dao: MenuDishTextMapper, number: Long) : BaseTestEntity<MenuDishText, MenuDishTextMapper>(dao, number) {
    override fun toList(number: Long) {
        for (i in 0 until number) {
            val s = UUID.randomUUID().toString()
            val n = (Math.random() * 10000 + 1).toInt()
            val menuDish = MenuDishText(0, s, s, n, n)
            dao.insert(menuDish)
            list.add(menuDish)
        }
    }
}
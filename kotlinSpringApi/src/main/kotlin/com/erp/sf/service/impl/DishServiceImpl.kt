package com.erp.sf.service.impl

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.erp.sf.mapper.MenuDishMapper
import com.erp.sf.entity.MenuDish
import com.erp.sf.service.DishService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DishServiceImpl : DishService {
    @Autowired
    private lateinit var menuDishMapper: MenuDishMapper

    override fun selectAllDish(): List<MenuDish> = menuDishMapper.selectList(null)

    override fun selectAllDishWithVisible(): List<MenuDish> {
        val lambdaQueryWrapper: LambdaQueryWrapper<MenuDish> = LambdaQueryWrapper<MenuDish>()
        lambdaQueryWrapper.eq(MenuDish::visible, true)
        return menuDishMapper.selectList(lambdaQueryWrapper)
    }

    override fun insertDish(dish: MenuDish): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteDish(dish: MenuDish): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateDish(dish: MenuDish): Boolean {
        TODO("Not yet implemented")
    }
}
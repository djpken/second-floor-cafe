package com.erp.sf.service.impl

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper
import com.erp.sf.mapper.DishMapper
import com.erp.sf.model.Dish
import com.erp.sf.service.DishService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DishServiceImpl : DishService {
    @Autowired
    private lateinit var dishMapper: DishMapper

    override fun selectAllDish(): List<Dish> = dishMapper.selectList(null)

    override fun selectAllDishWithVisible(): List<Dish> {
        val lambdaQueryWrapper: LambdaQueryWrapper<Dish> = LambdaQueryWrapper<Dish>()
        lambdaQueryWrapper.eq(Dish::visible, true)
        return dishMapper.selectList(lambdaQueryWrapper)
    }

    override fun insertDish(dish: Dish): Boolean {
        TODO("Not yet implemented")
    }

    override fun deleteDish(dish: Dish): Boolean {
        TODO("Not yet implemented")
    }

    override fun updateDish(dish: Dish): Boolean {
        TODO("Not yet implemented")
    }
}
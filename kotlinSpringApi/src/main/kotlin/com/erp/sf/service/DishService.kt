package com.erp.sf.service

import com.erp.sf.model.Dish

interface DishService  {
    fun selectAllDish(): List<Dish>
    fun selectAllDishWithVisible(): List<Dish>
    fun insertDish(dish: Dish): Boolean
    fun deleteDish(dish: Dish): Boolean
    fun updateDish(dish: Dish): Boolean

}
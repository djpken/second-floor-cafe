package com.erp.sf.controller

import com.erp.sf.model.ApiResponse
import com.erp.sf.service.DishService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/dish")
class DishController {
    @Autowired
    private lateinit var dishService: DishService
    @GetMapping
    fun selectAllDish():ResponseEntity<ApiResponse<Any>>{
        val selectAllDishWithVisible = dishService.selectAllDishWithVisible()
        return ResponseEntity.ok(ApiResponse.success(selectAllDishWithVisible,""))
    }
}
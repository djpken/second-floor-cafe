package com.erp.sf.controller

import com.erp.sf.service.MenuDishService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dish")
class DishController {
    @Autowired
    private lateinit var menuDishService: MenuDishService

    @GetMapping("{season}")
    public fun selectMenuDishBySeason(@PathVariable season: Long) {
        menuDishService.selectMenuDishBySeason(season);
    }
}
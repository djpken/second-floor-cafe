package com.erp.sf.controller

import com.erp.sf.entity.MenuDishPhoto
import com.erp.sf.model.ResponseEntity
import com.erp.sf.model.responseEntity.apiResposne.MenuDishModel
import com.erp.sf.service.MenuDishPhotoService
import com.erp.sf.service.MenuDishTextService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/dish")
class MenuDishController {
    @Autowired
    private lateinit var menuDishTextService: MenuDishTextService

    @Autowired
    private lateinit var menuDishPhotoService: MenuDishPhotoService

    @GetMapping("{season}")
    fun selectMenuDishBySeason(@PathVariable season: Long): ResponseEntity<MenuDishModel> {
        val selectMenuDishBySeason = menuDishTextService.selectMenuDishTextBySeason(season);
        return ResponseEntity.ok(MenuDishModel(selectMenuDishBySeason))
    }

    @PostMapping("")
    fun insertMenuDish(@RequestBody listMenuDishModel: MenuDishModel): ResponseEntity<MenuDishModel> {
        val listMenuDishText = listMenuDishModel.textArray
        val mapMenuDishPhoto = listMenuDishModel.photoMap
        menuDishTextService.insertListMenuDishText(listMenuDishText)
        menuDishPhotoService.insertMapMenuDishPhoto(mapMenuDishPhoto.map {MenuDishPhoto(0,it.key,it.value.bytes)})
        return ResponseEntity.ok(MenuDishModel(listMenuDishText))
    }
}
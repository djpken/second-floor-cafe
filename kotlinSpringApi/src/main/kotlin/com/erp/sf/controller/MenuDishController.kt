package com.erp.sf.controller

import com.erp.sf.entity.MenuDishPhoto
import com.erp.sf.model.ResponseEntity
import com.erp.sf.model.responseEntity.apiResposne.MenuDishModel
import com.erp.sf.service.MenuDishPhotoService
import com.erp.sf.service.MenuDishTextService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.mock.web.MockMultipartFile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/dish")
class MenuDishController {
    @Autowired
    private lateinit var menuDishTextService: MenuDishTextService

    @Autowired
    private lateinit var menuDishPhotoService: MenuDishPhotoService

    @GetMapping("{season}")
    fun selectMenuDishBySeason(@PathVariable season: Int): ResponseEntity<List<MenuDishModel>> {
        val selectMenuDishText = menuDishTextService.selectMenuDishTextBySeason(season);
        val selectMenuDishPhoto = menuDishPhotoService.selectMenuDishPhotoBySeason(season)
        val list = selectMenuDishText.mapIndexed { index, it ->
            (MenuDishModel(
                it, MockMultipartFile(
                    "photo$index.jpg",
                    selectMenuDishPhoto[index].data
                )
            ))
        }
        return ResponseEntity.ok(list)
    }
    @PostMapping("")
    fun insertMultipartFile(
        @RequestBody menuDishModelList: List<MenuDishModel>,
    ): ResponseEntity<List<MenuDishModel>> {
        val menuDishTextList = menuDishModelList.map { it.menuDishText }
        menuDishTextService.saveBatch(menuDishTextList)
        val menuDishPhotoList = menuDishModelList.mapIndexed { index, it ->
            MenuDishPhoto(
                0,
                menuDishTextList[index].id,
                it.multipartFile?.bytes
            )
        }
        menuDishPhotoService.saveBatch(menuDishPhotoList)
        val list =
            menuDishTextList.mapIndexed { index, it -> MenuDishModel(it, menuDishModelList[index].multipartFile) }
        return ResponseEntity.ok(list)
    }
}
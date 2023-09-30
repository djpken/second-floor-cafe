package com.erp.sf.controller

import com.erp.sf.constant.PATH
import com.erp.sf.entity.MenuDishPhoto
import com.erp.sf.entity.MenuDishText
import com.erp.sf.model.ResponseEntity
import com.erp.sf.model.responseEntity.apiResposne.MenuDishModel
import com.erp.sf.service.MenuDishPhotoService
import com.erp.sf.service.MenuDishTextService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.*

@RestController
@RequestMapping(PATH.MENU_DISH)
class MenuDishController {
    companion object {
        const val GET_PATH_VARIABLE_SEASON = "/{season}"
        const val POST_MENU_DISH_TEXT = "/menuDishText"
        const val POST_MENU_DISH_PHOTO = "/menuDishPhoto"
        const val PUT = "/"
    }

    @Autowired
    private lateinit var menuDishTextService: MenuDishTextService

    @Autowired
    private lateinit var menuDishPhotoService: MenuDishPhotoService

    @GetMapping(GET_PATH_VARIABLE_SEASON)
    fun selectMenuDishPhotoById(@PathVariable season: Int): ResponseEntity<List<MenuDishModel>> {
        val selectMenuDishText = menuDishTextService.selectMenuDishTextBySeason(season);
        val idList = selectMenuDishText.map { it.id ?: 0 }
        val stringList = menuDishPhotoService.selectMenuDishPhotoById(idList)
            .map { Base64.getEncoder().encodeToString(it.data) }
        val menuDishModelList = stringList.mapIndexed { index, s -> MenuDishModel(selectMenuDishText[index], s) }
        return ResponseEntity.ok(menuDishModelList)
    }

    @PostMapping(POST_MENU_DISH_TEXT)
    fun insertMenuDishText(@RequestBody menuDishText: List<MenuDishText>): ResponseEntity<List<MenuDishText>> {
        menuDishText.forEach() {
            it.id = 0
            it.chineseName = ""
            it.englishName = ""
            it.price = 0
        }
        val response = menuDishTextService.insertListMenuDishText(menuDishText)
        return ResponseEntity.ok(response)
    }

    @PostMapping(POST_MENU_DISH_PHOTO)
    fun insertMultipartFile(
        @RequestParam files: List<MultipartFile>,
    ): ResponseEntity<String> {
        val fileList = files.filter { it.originalFilename != null }
            .map {
                val stringList = it.originalFilename?.split('.')
                val fileName = stringList?.get(0)
                val fileExtension = stringList?.get(1)
                val fileId = stringList?.get(2)
                MenuDishPhoto(
                    0,
                    fileId?.toLong(),
                    fileName,
                    it.bytes
                )
            }
        menuDishPhotoService.saveBatch(fileList)
        return ResponseEntity.ok("ok")
    }

    @PutMapping(PUT)
    fun updateMenuDishText(@RequestBody menuDishText: MenuDishText): ResponseEntity<MenuDishText> {
        val response = menuDishTextService.updateMenuDishText(menuDishText)
        return ResponseEntity.ok(response)
    }
}
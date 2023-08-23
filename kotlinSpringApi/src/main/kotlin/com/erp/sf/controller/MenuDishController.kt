package com.erp.sf.controller

import com.erp.sf.entity.MenuDishPhoto
import com.erp.sf.model.ResponseEntity
import com.erp.sf.model.responseEntity.apiResposne.MenuDishModel
import com.erp.sf.service.MenuDishPhotoService
import com.erp.sf.service.MenuDishTextService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import java.util.Base64

@RestController
@RequestMapping("/menuDish")
class MenuDishController {
    @Autowired
    private lateinit var menuDishTextService: MenuDishTextService

    @Autowired
    private lateinit var menuDishPhotoService: MenuDishPhotoService

//    @GetMapping("{season}")
//    fun selectMenuDishBySeason(@PathVariable season: Int): ResponseEntity<List<MenuDishText>> {
//        val selectMenuDishText = menuDishTextService.selectMenuDishTextBySeason(season);
//        return ResponseEntity.ok(selectMenuDishText)
//    }

    @GetMapping("{season}")
    fun selectMenuDishPhotoById(@PathVariable season: Int): ResponseEntity<List<MenuDishModel>> {
        val selectMenuDishText = menuDishTextService.selectMenuDishTextBySeason(season);
        val idList = selectMenuDishText.map { it.id ?: 0 }
        val stringList = menuDishPhotoService.selectMenuDishPhotoById(idList)
            .map { Base64.getEncoder().encodeToString(it.data) }
        val menuDishModelList = stringList.mapIndexed { index, s -> MenuDishModel(selectMenuDishText[index], s) }
        return ResponseEntity.ok(menuDishModelList)
    }

    @PostMapping("")
    fun insertMultipartFile(
        @RequestParam files: List<MultipartFile>,
    ): ResponseEntity<String> {
        val fileList = files.filter { it.originalFilename != null }
            .map {
                MenuDishPhoto(
                    0,
                    it.originalFilename!!.substring(0, it.originalFilename!!.length - 4).toLong(),
                    it.bytes
                )
            }
        menuDishPhotoService.saveBatch(fileList)
        return ResponseEntity.ok("ok")
    }
}
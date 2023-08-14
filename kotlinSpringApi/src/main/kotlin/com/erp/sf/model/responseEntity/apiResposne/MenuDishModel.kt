package com.erp.sf.model.responseEntity.apiResposne

import com.erp.sf.entity.MenuDishText
import com.erp.sf.entity.MenuDishPhoto
import org.springframework.web.multipart.MultipartFile
import java.io.Serializable

data class MenuDishModel(val textArray: List<MenuDishText>, val photoMap: Map<Long, MultipartFile>) :
    Serializable {
    constructor(menuDishText: List<MenuDishText>) : this(menuDishText, emptyMap<Long, MultipartFile>())
}
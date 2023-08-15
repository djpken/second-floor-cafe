package com.erp.sf.model.responseEntity.apiResposne

import com.erp.sf.entity.MenuDishText
import com.erp.sf.model.json.PhotoFile
import java.io.Serializable

data class MenuDishModel(val textArray: List<MenuDishText>, val photoArray: List<PhotoFile>) :
    Serializable {
    constructor(menuDishText: List<MenuDishText>) : this(menuDishText, emptyList())
}
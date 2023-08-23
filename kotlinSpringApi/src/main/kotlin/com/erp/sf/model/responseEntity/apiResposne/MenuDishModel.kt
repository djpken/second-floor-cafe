package com.erp.sf.model.responseEntity.apiResposne

import com.erp.sf.entity.MenuDishText
import org.springframework.web.multipart.MultipartFile
import java.io.Serializable

data class MenuDishModel(val menuDishText: MenuDishText, val string: String) :
    Serializable {
}
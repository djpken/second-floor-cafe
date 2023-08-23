package com.erp.sf.entity

import com.baomidou.mybatisplus.annotation.*
import java.io.Serializable

@TableName(value = "menu_dish_photo")
data class MenuDishPhoto(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,
    var menuDishId: Long? = null,
    var data: ByteArray? = null,
): Serializable

package com.erp.sf.entity

import com.baomidou.mybatisplus.annotation.*
import com.fasterxml.jackson.annotation.JsonFormat
import java.io.Serializable
import java.time.LocalDateTime

@TableName(value = "menu_dish_photo")
data class MenuDishPhoto(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,
    var menuDIshId: Long? = null,
    var data: ByteArray? = null,
): Serializable

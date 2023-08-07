package com.erp.sf.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableLogic
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.time.LocalDateTime

@TableName(value = "MENU_DISH")
data class MenuDish(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,
    var chineseName: String = "",
    var englishName: String = "",
    var price: Int = 0,
    var season: Int = 0,
    @JsonIgnore
    var visible: Boolean = false,
    @JsonIgnore
    @TableLogic(value = "null", delval = "now()")
    var deleteAt: LocalDateTime? = null
) : Serializable

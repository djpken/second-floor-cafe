package com.erp.sf.entity

import com.baomidou.mybatisplus.annotation.*
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.sql.Date
import java.time.LocalDateTime

@TableName(value = "SYS_MENU")
data class SysMenu(
    @TableId(type = IdType.AUTO)
    var id: Long?=null,
    var perms: String="",
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var createAt: LocalDateTime? = null,
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    var updateAt: LocalDateTime? = null,
    @TableLogic(value = "null", delval = "now()")
    @JsonIgnore
    var deleteAt: LocalDateTime? = null

) : Serializable

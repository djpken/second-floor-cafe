package com.erp.sf.entity

import com.baomidou.mybatisplus.annotation.*
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonIgnore
import java.io.Serializable
import java.sql.Date
import java.time.LocalDateTime
import java.util.UUID

@TableName(value = "SYS_USER")
data class SysUser(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,
    var username: String = UUID.randomUUID().toString(),
    var password: String = UUID.randomUUID().toString(),
    var chineseName: String = "",
    var englishName: String = "",
    var email: String = UUID.randomUUID().toString(),
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

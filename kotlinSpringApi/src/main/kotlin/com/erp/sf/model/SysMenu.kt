package com.erp.sf.model

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.sql.Date

@TableName(value = "sys_menu")
data class SysMenu(
    @TableId(type = IdType.ASSIGN_ID)
    var id:Long?,
    var menu:String,
    var perms:String,
    var deleteAt : Date
):Serializable

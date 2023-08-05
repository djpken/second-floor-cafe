package com.erp.sf.model

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.sql.Date

@TableName(value = "sys_role")
data class SysRole(
    @TableId(type=IdType.ASSIGN_ID)
    var id:Long?,
    var name:String,
    var deleteAt: Date
):Serializable

package com.erp.sf.model

import com.baomidou.mybatisplus.annotation.FieldFill
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable
import java.sql.Date

@TableName(value = "employee")
data class Employee(
    var id: Long?,
    var username:String,
    var password:String,
    var name:String,
    var admin: Boolean,
    @TableField(fill = FieldFill.INSERT)
    var createAt: Date,
    @TableField(fill = FieldFill.INSERT_UPDATE)
    var updateAt: Date,
    var score:Long,
    var deleteAt:Date,
    var email:String
):Serializable

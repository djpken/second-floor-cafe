package com.erp.sf.model

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.fasterxml.jackson.annotation.JsonIgnore
import lombok.Data
import java.io.Serializable

@TableName(value = "employee")
data class Dish(
    @TableId(type = IdType.ASSIGN_ID)
    var id:Long?,
    var name:String,
    @JsonIgnore
    var visible:Boolean
) :Serializable

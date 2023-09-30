package com.erp.sf.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

@TableName(value = "setting")
data class Setting(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,
    var key: String = "",
    var value: String = ""
) : Serializable

package com.erp.sf.entity

import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

@TableName(value = "setting")
data class Setting(
    var key: String? = null,
    var value: String? = null
): Serializable

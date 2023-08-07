package com.erp.sf.entity

import com.baomidou.mybatisplus.annotation.TableName

@TableName(value = "SYS_USER_ROLE")
data class SysUserRole(
    var userId: String? = null,
    var roleId: String? = null
)

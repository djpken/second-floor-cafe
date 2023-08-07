package com.erp.sf.entity

import com.baomidou.mybatisplus.annotation.TableName

@TableName(value="SYS_ROLE_MENU")
data class SysRoleMenu(
    var roleId: Long? = null,
    var menuId: Long? = null,
)

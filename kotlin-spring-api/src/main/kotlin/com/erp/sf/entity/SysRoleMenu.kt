package com.erp.sf.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

@TableName(value = "SYS_ROLE_MENU")
data class SysRoleMenu(
    @TableId(type = IdType.AUTO)
    var id: Long? = null,
    var roleId: Long? = null,
    var menuId: Long? = null,
): Serializable

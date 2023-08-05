package com.erp.sf.model

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

@TableName(value = "sys_role_menu")
data class SysRoleMenu(
    @TableId
    var roleId:Long?,
    var menuId:Long,
): Serializable

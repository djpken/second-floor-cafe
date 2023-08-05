package com.erp.sf.model

import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import java.io.Serializable

@TableName(value = "sys_user_role")
data class SysEmployeeRole(
    @TableId
    var employeeId: Long?,
    var roleId:Long
): Serializable

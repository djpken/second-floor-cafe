package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.entity.SysUser
import com.erp.sf.entity.SysUserRole
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface SysUserRoleMapper : BaseMapper<SysUserRole> {
}
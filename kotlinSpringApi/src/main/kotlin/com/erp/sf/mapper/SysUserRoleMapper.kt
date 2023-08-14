package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.entity.SysUser
import com.erp.sf.entity.SysUserRole
import com.erp.sf.mapper.aBase.MyBaseMapper
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface SysUserRoleMapper : MyBaseMapper<SysUserRole> {
}
package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.entity.SysUser
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param

@Mapper
interface SysUserMapper:BaseMapper<SysUser> {
    fun selectOneByUsername(@Param("username") username:String): SysUser
}
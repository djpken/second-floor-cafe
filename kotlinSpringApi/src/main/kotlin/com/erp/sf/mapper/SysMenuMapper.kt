package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.entity.SysMenu
import com.erp.sf.mapper.aBase.MyBaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface SysMenuMapper: MyBaseMapper<SysMenu> {
    fun selectPermsByUserId(id: Long): List<String>
}
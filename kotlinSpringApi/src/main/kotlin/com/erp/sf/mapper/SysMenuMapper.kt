package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.model.SysMenu
import org.apache.ibatis.annotations.Mapper

@Mapper
interface SysMenuMapper: BaseMapper<SysMenu> {
    fun selectPermsByUserId(id: Long): List<String>
}
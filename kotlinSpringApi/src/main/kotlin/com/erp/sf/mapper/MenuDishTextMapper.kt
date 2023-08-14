package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.entity.MenuDishText
import com.erp.sf.mapper.aBase.MyBaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface MenuDishTextMapper : MyBaseMapper<MenuDishText> {
}
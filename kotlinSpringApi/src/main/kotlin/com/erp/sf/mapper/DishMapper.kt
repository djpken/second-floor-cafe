package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.model.Dish
import org.apache.ibatis.annotations.Mapper

@Mapper
interface DishMapper :BaseMapper<Dish> {
}
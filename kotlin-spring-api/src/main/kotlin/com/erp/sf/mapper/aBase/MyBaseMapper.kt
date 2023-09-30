package com.erp.sf.mapper.aBase

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Param

interface MyBaseMapper<T> : BaseMapper<T> {
    fun insertBatch(@Param("list") list: List<T>): Int
}
package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.entity.Score
import org.apache.ibatis.annotations.Mapper

@Mapper
interface ScoreMapper : BaseMapper<Score> {
}
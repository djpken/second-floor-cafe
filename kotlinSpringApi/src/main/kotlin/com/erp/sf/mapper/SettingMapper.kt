package com.erp.sf.mapper

import cn.hutool.setting.Setting
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface SettingMapper : BaseMapper<Setting> {
    fun selectAllSetting(): List<Setting>
    fun selectValueByKey(key:String): String
}
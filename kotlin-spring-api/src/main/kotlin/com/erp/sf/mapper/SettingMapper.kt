package com.erp.sf.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.entity.Setting
import com.erp.sf.mapper.aBase.MyBaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface SettingMapper : MyBaseMapper<Setting> {
    fun selectAllSetting(): List<Setting>
    fun selectValueByKey(key: String): String
    fun insertSetting(setting: Setting): Long
    fun updateSetting(setting: Setting): Long
}
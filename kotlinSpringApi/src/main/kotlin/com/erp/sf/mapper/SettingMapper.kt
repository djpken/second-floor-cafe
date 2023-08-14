package com.erp.sf.mapper

import cn.hutool.setting.Setting
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.erp.sf.mapper.aBase.MyBaseMapper
import org.apache.ibatis.annotations.Mapper

@Mapper
interface SettingMapper : MyBaseMapper<Setting> {
    fun selectAllSetting(): List<Setting>
    fun selectValueByKey(key:String): String
}
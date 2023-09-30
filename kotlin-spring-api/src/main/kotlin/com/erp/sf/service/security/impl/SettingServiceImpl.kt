package com.erp.sf.service.security.impl

import com.baomidou.mybatisplus.core.conditions.Wrapper
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.baomidou.mybatisplus.extension.kotlin.KtUpdateWrapper
import com.erp.sf.entity.Setting
import com.erp.sf.mapper.SettingMapper
import com.erp.sf.service.security.SettingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.function.Function

@Service
class SettingServiceImpl : SettingService {
    @Autowired
    private lateinit var settingMapper: SettingMapper
    override fun selectAllSetting(): List<Setting> {
        return settingMapper.selectAllSetting()
    }

    override fun insertSetting(setting: Setting): Setting {
        settingMapper.insertSetting(setting)
        return setting
    }

    override fun updateSetting(setting: Setting): Setting {
        settingMapper.updateSetting(setting)
        return setting
    }
}
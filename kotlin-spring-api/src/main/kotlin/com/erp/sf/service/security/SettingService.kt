package com.erp.sf.service.security

import com.baomidou.mybatisplus.extension.service.IService
import com.erp.sf.entity.Setting

interface SettingService {
    fun selectAllSetting(): List<Setting>
    fun insertSetting(setting: Setting): Setting
    fun updateSetting(setting: Setting): Setting
}
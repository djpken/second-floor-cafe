package com.erp.sf.service.security

import com.erp.sf.JunitService
import com.erp.sf.entity.Setting
import com.erp.sf.entity.impl.SettingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SettingServiceImplTest : JunitService() {

    private lateinit var settingTest: SettingTest
    @BeforeEach
    override fun beforeEach() {
        settingTest = SettingTest(settingMapper, number)
    }

    @Test
    fun selectAllSetting() {
        val selectAllSetting = settingService.selectAllSetting()
        println(selectAllSetting)
    }

    @Test
    fun insertSetting() {
        val setting = Setting(0, "key", "123456")
        settingService.insertSetting(setting)
        println(setting)
    }
    @Test
    fun updateSetting(){
    }
}
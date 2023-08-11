package com.erp.sf.mapper

import com.erp.sf.JunitMapper
import com.erp.sf.JunitService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class SettingMapperTest : JunitService() {
    @Autowired
    private lateinit var settingMapper: SettingMapper

    @BeforeEach
    override fun beforeEach() {
    }

    @Test
    public fun selectAllSetting() {
        val selectAllSetting = settingMapper.selectAllSetting()
        println(selectAllSetting)
    }

    @Test
    public fun selectValueByKey() {
        val value = settingMapper.selectValueByKey("inviteCode")
        println(value)
    }

}
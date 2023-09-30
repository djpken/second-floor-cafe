package com.erp.sf.mapper

import com.erp.sf.JunitMapper
import com.erp.sf.JunitService
import com.erp.sf.entity.Setting
import com.erp.sf.entity.impl.SettingTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.util.*

class SettingMapperTest : JunitService() {
    private lateinit var settingTest: SettingTest

    @BeforeEach
    override fun beforeEach() {
        settingTest = SettingTest(settingMapper, number)
    }

    @Test
    public fun selectAllSetting() {
        val selectAllSetting = settingMapper.selectAllSetting()
        val actual = selectAllSetting.size
        Assertions.assertNotEquals(settingTest.beforeCount + number, actual)
    }

    @Test
    public fun selectValueByKey() {
        for (j in 0 until number) {
            val i = j.toInt()
            val actual = settingMapper.selectValueByKey(settingTest.list[i].key)
            Assertions.assertNotNull(actual)
        }
    }

    @Test
    public fun insertSetting() {
        val expected = settingTest.afterCount
        for (i in 0 until number) {
            val random = UUID.randomUUID().toString()
            val actual = settingMapper.insertSetting(Setting(0, random, random))
        }

        Assertions.assertEquals(expected + number, settingMapper.selectCount(null))
    }

    @Test
    public fun updateSetting() {
        for (i in 0 until number) {
            val j = i.toInt()
            val expected = UUID.randomUUID().toString()
            settingMapper.updateSetting(Setting(0, settingTest.list[j].key, expected))
            Assertions.assertNotEquals(
                settingTest.list[j].value,
                settingMapper.selectValueByKey(settingTest.list[j].key)
            )
        }
    }
}
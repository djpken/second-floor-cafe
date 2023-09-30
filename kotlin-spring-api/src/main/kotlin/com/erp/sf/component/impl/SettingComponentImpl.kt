package com.erp.sf.component.impl

import com.erp.sf.mapper.SettingMapper
import com.erp.sf.component.RedisComponent
import com.erp.sf.component.SettingComponent
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import com.erp.sf.constant.S

@Component
class SettingComponentImpl : SettingComponent {
    @Autowired
    private lateinit var redisUtil: RedisComponent

    @Autowired
    private lateinit var settingMapper: SettingMapper
    override fun get(setting: S): String {

        val value = redisUtil["setting:${setting.key}"] ?: ""
        if (value != "") {
            return value.toString();
        }
        val selectValue = settingMapper.selectValueByKey(setting.key)
        redisUtil["setting:${setting.key}"] = selectValue
        return selectValue
    }
}
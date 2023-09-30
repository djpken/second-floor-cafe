package com.erp.sf.controller.security

import com.erp.sf.component.SettingComponent
import com.erp.sf.constant.C
import com.erp.sf.constant.M
import com.erp.sf.constant.PATH
import com.erp.sf.constant.S
import com.erp.sf.entity.Setting
import com.erp.sf.model.ResponseEntity
import com.erp.sf.service.security.SettingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping(PATH.SETTING)
class SettingController {
    companion object {
        const val GET = "/"
        const val GET_KEY = "{key}"
        const val POST = "/"
        const val PUT = "/"
    }

    @Autowired
    private lateinit var settingService: SettingService

    @Autowired
    private lateinit var settingComponent: SettingComponent

    @GetMapping(GET)
    fun selectAllSetting(): ResponseEntity<Map<String, String>> {
        val map = HashMap<String, String>()
        settingService.selectAllSetting().forEach { map[it.key] = it.value }
        return ResponseEntity.ok(map)
    }

    @GetMapping(GET_KEY)
    fun selectGetValueByKey(@PathVariable key: String): ResponseEntity<String> {
        val value = S.entries.find { it.key == key } ?: return ResponseEntity.badRequest(C.DATA_FAILED, M.SETTING_NULL)
        return ResponseEntity.ok(settingComponent.get(value))
    }


    @PostMapping(POST)
    fun insertPost(setting: Setting): ResponseEntity<Map<String, String>> {
        val map = HashMap<String, String>()
        val insertSetting = settingService.insertSetting(setting)
        map[insertSetting.key] = insertSetting.value
        return ResponseEntity.ok(map)
    }

    @PutMapping(PUT)
    fun updateSetting(setting: Setting): ResponseEntity<Map<String, String>> {
        val map = HashMap<String, String>()
        val updateSetting = settingService.updateSetting(setting)
        map[updateSetting.key] = updateSetting.value
        return ResponseEntity.ok(map)
    }
}

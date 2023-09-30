package com.erp.sf

import com.erp.sf.component.PasswordComponent
import com.erp.sf.component.RedisComponent
import com.erp.sf.component.SettingComponent
import com.erp.sf.mapper.*
import com.erp.sf.service.MenuDishPhotoService
import com.erp.sf.service.MenuDishTextService
import com.erp.sf.service.ScoreService
import com.erp.sf.service.security.LoginService
import com.erp.sf.service.security.SettingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.transaction.annotation.Transactional


@Transactional
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class JunitService : BeforeEach {
    @Autowired
    lateinit var settingComponent: SettingComponent

    @Autowired
    lateinit var menuDishPhotoService: MenuDishPhotoService

    @Autowired
    lateinit var menuDishTextService: MenuDishTextService

    @Autowired
    lateinit var menuDishMapper: MenuDishTextMapper

    @Autowired
    lateinit var scoreService: ScoreService

    @Autowired
    lateinit var passwordParser: PasswordComponent

    @Autowired
    lateinit var userDetailService: UserDetailsService

    @Autowired
    lateinit var settingService: SettingService

    @Autowired
    lateinit var redisUtil: RedisComponent

    @Autowired
    lateinit var loginService: LoginService

    @Autowired
    lateinit var menuDishTextMapper: MenuDishTextMapper

    @Autowired
    lateinit var settingMapper: SettingMapper

    @Autowired
    lateinit var sysUserMapper: SysUserMapper

    @Autowired
    lateinit var sysRoleMapper: SysRoleMapper

    @Autowired
    lateinit var sysMenuMapper: SysMenuMapper

    @Autowired
    lateinit var sysUserRoleMapper: SysUserRoleMapper

    @Autowired
    lateinit var sysRoleMenuMapper: SysRoleMenuMapper

    @Autowired
    lateinit var scoreMapper: ScoreMapper


    companion object {
        const val number: Long = 3
    }
}
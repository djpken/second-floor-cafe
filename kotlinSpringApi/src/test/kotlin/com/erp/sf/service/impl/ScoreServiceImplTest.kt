package com.erp.sf.service.impl

import com.erp.sf.JunitService
import com.erp.sf.entity.Score
import com.erp.sf.entity.impl.SysUserTest
import com.erp.sf.mapper.ScoreMapper
import com.erp.sf.mapper.SysUserMapper
import com.erp.sf.service.security.ScoreService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ScoreServiceImplTest : JunitService() {
    @Autowired
    private lateinit var scoreService: ScoreService

    @Autowired
    private lateinit var sysUserMapper: SysUserMapper

    @Autowired
    private lateinit var scoreMapper: ScoreMapper
    private lateinit var sysUserTest: SysUserTest
    val number = 5
    var beforeCount = 0

    @BeforeEach
    override fun beforeEach() {
        sysUserTest = SysUserTest(sysUserMapper, 5)
        for (i in 0 until 5) {
            val score = Score(0, sysUserTest.list[i].id, Math.random().toInt())
            scoreMapper.insert(score)
        }
        beforeCount = scoreMapper.selectCount(null).toInt()
    }

    @Test
    fun insertScore() {
        val score = Score(0, sysUserTest.list[0].id, Math.random().toInt())
        val insertScore = scoreService.insertScore(score)
        val afterCount = scoreMapper.selectCount(null).toInt()
        Assertions.assertEquals(beforeCount + 1, afterCount)
    }

    @Test
    fun selectScoreByUserId() {
        val score = sysUserTest.list[0].id?.let { scoreService.selectScoreByUserId(it) }
        Assertions.assertNotNull(score)
    }

}
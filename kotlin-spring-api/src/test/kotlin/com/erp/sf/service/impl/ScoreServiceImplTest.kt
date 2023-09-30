package com.erp.sf.service.impl

import com.erp.sf.JunitService
import com.erp.sf.entity.Score
import com.erp.sf.entity.impl.SysUserTest
import com.erp.sf.service.ScoreService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ScoreServiceImplTest : JunitService() {

    private lateinit var sysUserTest: SysUserTest
    var beforeCount = 0

    @BeforeEach
    override fun beforeEach() {
        sysUserTest = SysUserTest(sysUserMapper, 5)
        val list = mutableListOf<Score>()
        for (i in 0 until 5) {
            val score = Score(0, sysUserTest.list[i].id, Math.random().toInt())
            scoreMapper.insert(score)
            list.add(score)
        }
        beforeCount = scoreMapper.selectCount(null).toInt()

    }

    @Test
    fun insertScore() {
        val score = Score(0, sysUserTest.list[0].id, Math.random().toInt())
        scoreService.insertScore(score)
        val afterCount = scoreMapper.selectCount(null).toInt()
        Assertions.assertEquals(beforeCount + 1, afterCount)
    }

    @Test
    fun selectScoreByUserId() {
        val score = sysUserTest.list[0].id?.let { scoreService.selectScoreByUserId(it) }
        Assertions.assertNotNull(score)
    }

}
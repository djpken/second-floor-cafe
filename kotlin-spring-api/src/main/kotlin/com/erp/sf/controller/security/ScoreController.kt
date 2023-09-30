package com.erp.sf.controller.security

import com.erp.sf.constant.PATH
import com.erp.sf.entity.Score
import com.erp.sf.model.ResponseEntity
import com.erp.sf.service.ScoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping(PATH.SCORE)
class ScoreController {
    companion object {
        const val POST = ""
        const val GET_PATH_USER_ID = "{userId}"
    }

    @Autowired
    private lateinit var scoreService: ScoreService

    @PostMapping(POST)
    fun insertScore(score: Score): ResponseEntity<Score> {
        val insertScore = scoreService.insertScore(score)
        return ResponseEntity.ok(insertScore)
    }

    @GetMapping(GET_PATH_USER_ID)
    fun selectScoreByUserId(@PathVariable userId: Long): ResponseEntity<List<Score>> {
        val selectScoreByUserId = scoreService.selectScoreByUserId(userId)
        return ResponseEntity.ok(selectScoreByUserId)
    }


}
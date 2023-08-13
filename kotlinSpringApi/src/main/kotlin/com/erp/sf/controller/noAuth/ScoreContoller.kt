package com.erp.sf.controller.noAuth

import com.erp.sf.entity.Score
import com.erp.sf.model.ResponseEntity
import com.erp.sf.service.security.ScoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/score")
class ScoreContoller {
    @Autowired
    private lateinit var scoreService: ScoreService

    @PostMapping("")
    fun insertScore(score: Score): ResponseEntity<Any> {
        val insertScore = scoreService.insertScore(score)
        return ResponseEntity.ok(insertScore)
    }

    @GetMapping("{userId}")
    fun selectScoreByUserId(@PathVariable userId: Long): ResponseEntity<Any> {
        val selectScoreByUserId = scoreService.selectScoreByUserId(userId)
        return ResponseEntity.ok(selectScoreByUserId)
    }


}
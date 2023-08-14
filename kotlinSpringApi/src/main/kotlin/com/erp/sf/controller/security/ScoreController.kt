package com.erp.sf.controller.security

import com.erp.sf.entity.Score
import com.erp.sf.model.ResponseEntity
import com.erp.sf.service.ScoreService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/score")
class ScoreController {
    @Autowired
    private lateinit var scoreService: ScoreService

    @PostMapping("")
    fun insertScore(score: Score): ResponseEntity<Score> {
        val insertScore = scoreService.insertScore(score)
        return ResponseEntity.ok(insertScore)
    }

    @GetMapping("{userId}")
    fun selectScoreByUserId(@PathVariable userId: Long): ResponseEntity<List<Score>> {
        val selectScoreByUserId = scoreService.selectScoreByUserId(userId)
        return ResponseEntity.ok(selectScoreByUserId)
    }


}
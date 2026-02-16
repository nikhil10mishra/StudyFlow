package com.studyplanner.studyplanner.controller;

import com.studyplanner.studyplanner.service.GoalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/goals")
@CrossOrigin(origins = "http://localhost:8081")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @PutMapping("/{goalId}/complete")
    public ResponseEntity<String> completeGoal(@PathVariable String goalId) throws Exception {
        goalService.completeGoal(goalId);
        return ResponseEntity.ok("Goal completed");
    }
}

package com.studyplanner.studyplanner.controller;

import com.studyplanner.studyplanner.model.Goal;

import com.studyplanner.studyplanner.model.Goal;
import com.studyplanner.studyplanner.model.Subject;
import com.studyplanner.studyplanner.service.GoalService;
import com.studyplanner.studyplanner.service.SubjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
@CrossOrigin(origins = "http://localhost:8081")
public class SubjectController {

    private final SubjectService subjectService;
    private final GoalService goalService;

    public SubjectController(SubjectService subjectService,
                         GoalService goalService) {
    this.subjectService = subjectService;
    this.goalService = goalService;
}


    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() throws Exception {
        return ResponseEntity.ok(subjectService.getAllSubjects());
    }

    @PostMapping
public ResponseEntity<String> createSubject(@RequestBody Subject subject) throws Exception {
    subjectService.createSubject(subject);
    return ResponseEntity.ok("Subject created");
}

@PostMapping("/{subjectId}/goals")
public ResponseEntity<String> addGoal(
        @PathVariable String subjectId,
        @RequestBody Goal goal) throws Exception {

    goalService.addGoal(subjectId, goal);
    return ResponseEntity.ok("Goal added");
}


}

package com.studyflow.studyflow_backend.controller;

import com.studyflow.studyflow_backend.dto.GoalResponse;
import com.studyflow.studyflow_backend.dto.SubjectResponse;
import com.studyflow.studyflow_backend.entity.Subject;
import com.studyflow.studyflow_backend.entity.User;
import com.studyflow.studyflow_backend.repository.SubjectRepository;
import com.studyflow.studyflow_backend.repository.UserRepository;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectRepository subjectRepository;
    private final UserRepository userRepository;

    public SubjectController(
            SubjectRepository subjectRepository,
            UserRepository userRepository
    ) {
        this.subjectRepository = subjectRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public List<SubjectResponse> getAll(
            @RequestHeader("X-USER-ID") String userId) {

        return subjectRepository
                .findAllWithGoalsByUserId(userId)
                .stream()
                .map(subject -> {

                    List<GoalResponse> goals = subject.getGoals().stream()
                            .map(goal -> new GoalResponse(
                                    goal.getId(),
                                    goal.getTitle(),
                                    goal.isCompleted(),
                                    goal.getDate()
                            ))
                            .toList();

                    return new SubjectResponse(
                            subject.getId(),
                            subject.getName(),
                            subject.getIcon(),
                            subject.getColor(),
                            goals
                    );

                }).toList();
    }

    @PostMapping
    public ResponseEntity<SubjectResponse> create(
            @RequestHeader("X-USER-ID") String userId,
            @RequestBody Subject subject) {

        User user = userRepository.findById(userId)
                .orElseGet(() -> userRepository.save(new User(userId, "", "")));

        subject.setUser(user);

        Subject saved = subjectRepository.save(subject);

        SubjectResponse response = new SubjectResponse(
                saved.getId(),
                saved.getName(),
                saved.getIcon(),
                saved.getColor(),
                List.of()
        );

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        if (!subjectRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        subjectRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
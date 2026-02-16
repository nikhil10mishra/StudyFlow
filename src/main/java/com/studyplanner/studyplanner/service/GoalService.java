package com.studyplanner.studyplanner.service;

import com.studyplanner.studyplanner.model.Goal;
import com.studyplanner.studyplanner.repository.GoalRepository;
import org.springframework.stereotype.Service;

@Service
public class GoalService {

    private final GoalRepository goalRepository;

    public GoalService(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    public void addGoal(String subjectId, Goal goal) throws Exception {
        goalRepository.addGoal(subjectId, goal);
    }

    public void completeGoal(String goalId) throws Exception {
    goalRepository.completeGoal(goalId);
}

}

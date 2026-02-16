package com.studyplanner.studyplanner.service;

import com.studyplanner.studyplanner.dto.AnalyticsResponse;
import com.studyplanner.studyplanner.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalyticsService {

    private final SubjectService subjectService;

    public AnalyticsService(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    public AnalyticsResponse calculate() throws Exception {

        List<Subject> subjects = subjectService.getAllSubjects();

        int totalGoals = 0;
        int completedGoals = 0;

        for (Subject subject : subjects) {
            if (subject.getGoals() != null) {
                totalGoals += subject.getGoals().size();
                completedGoals += subject.getGoals()
                        .stream()
                        .filter(g -> g.isCompleted())
                        .count();
            }
        }

        double completionPercentage =
                totalGoals == 0 ? 0 :
                        (completedGoals * 100.0) / totalGoals;

        double weeklyProductivityScore = completionPercentage;

        return new AnalyticsResponse(
                weeklyProductivityScore,
                totalGoals,
                completedGoals,
                completionPercentage
        );
    }
}

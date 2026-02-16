package com.studyplanner.studyplanner.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AnalyticsResponse {

    private double weeklyProductivityScore;
    private int totalGoals;
    private int completedGoals;
    private double completionPercentage;
}

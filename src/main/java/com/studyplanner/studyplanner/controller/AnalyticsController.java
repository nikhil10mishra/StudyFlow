package com.studyplanner.studyplanner.controller;

import com.studyplanner.studyplanner.dto.AnalyticsResponse;
import com.studyplanner.studyplanner.service.AnalyticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping
    public ResponseEntity<AnalyticsResponse> getAnalytics() throws Exception {
        return ResponseEntity.ok(analyticsService.calculate());
    }
}

package com.studyplanner.studyplanner.model;

import lombok.Data;
import java.util.List;

@Data
public class Subject {

    private String id;
    private String name;
    private String description;
    private String color;
    private List<Goal> goals; // embedded when returning to frontend
}

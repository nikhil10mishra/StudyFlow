package com.studyplanner.studyplanner.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.studyplanner.studyplanner.model.Goal;
import org.springframework.stereotype.Repository;

@Repository
public class GoalRepository {

    private final Firestore firestore;

    public GoalRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public void addGoal(String subjectId, Goal goal) throws Exception {

        goal.setSubjectId(subjectId);
        goal.setCompleted(false);

        ApiFuture<DocumentReference> future =
                firestore.collection("goals").add(goal);

        future.get();
    }

    public void completeGoal(String goalId) throws Exception {

    firestore.collection("goals")
            .document(goalId)
            .update("completed", true);
}

}

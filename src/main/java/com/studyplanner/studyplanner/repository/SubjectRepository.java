package com.studyplanner.studyplanner.repository;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.studyplanner.studyplanner.model.Subject;
import com.studyplanner.studyplanner.model.Goal;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SubjectRepository {

    private final Firestore firestore;

    public SubjectRepository(Firestore firestore) {
        this.firestore = firestore;
    }

    public List<Subject> getAllSubjects() throws Exception {

        List<Subject> subjects = new ArrayList<>();

        ApiFuture<QuerySnapshot> future =
                firestore.collection("subjects").get();

        for (DocumentSnapshot doc : future.get().getDocuments()) {

            Subject subject = doc.toObject(Subject.class);
            subject.setId(doc.getId());

            ApiFuture<QuerySnapshot> goalsFuture =
                    firestore.collection("goals")
                            .whereEqualTo("subjectId", subject.getId())
                            .get();

            List<Goal> goals = goalsFuture.get().getDocuments()
                    .stream()
                    .map(g -> {
                        Goal goal = g.toObject(Goal.class);
                        goal.setId(g.getId());
                        return goal;
                    })
                    .toList();

            subject.setGoals(goals);
            subjects.add(subject);
        }

        return subjects;
    }

    public void createSubject(Subject subject) throws Exception {

    ApiFuture<DocumentReference> future =
            firestore.collection("subjects").add(subject);

    future.get(); // wait for completion
}

}

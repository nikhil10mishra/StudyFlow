package com.studyplanner.studyplanner.service;

import com.studyplanner.studyplanner.model.Subject;
import com.studyplanner.studyplanner.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public List<Subject> getAllSubjects() throws Exception {
        return subjectRepository.getAllSubjects();
    }

    public void createSubject(Subject subject) throws Exception {
    subjectRepository.createSubject(subject);
}

}

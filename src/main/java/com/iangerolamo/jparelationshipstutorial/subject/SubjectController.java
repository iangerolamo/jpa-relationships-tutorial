package com.iangerolamo.jparelationshipstutorial.subject;

import com.iangerolamo.jparelationshipstutorial.student.Student;
import com.iangerolamo.jparelationshipstutorial.student.StudentRepository;
import com.iangerolamo.jparelationshipstutorial.teacher.Teacher;
import com.iangerolamo.jparelationshipstutorial.teacher.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    TeacherRepository teacherRepository;

    @GetMapping
    List<Subject> getSubjects() {
        return subjectRepository.findAll();
    }

    @PostMapping
    Subject createSubject(@RequestBody Subject subject) {
        return subjectRepository.save(subject);
    }

    @PutMapping("/{subjectId}/students/{studentId}")
    Subject enrollStudentToSubject(
            @PathVariable Long studentId,
            @PathVariable Long subjectId
    ) {
        Subject subject = subjectRepository.findById(subjectId).get();
        Student student = studentRepository.findById(studentId).get();
        subject.enrollStudent(student);
        return subjectRepository.save(subject);
    }

    @PutMapping("/{subjectId}/teacher/{teacherId}")
    Subject assignTeacherToSubject(
            @PathVariable Long subjectId,
            @PathVariable Long teacherId
    ) {
        Subject subject = subjectRepository.findById(subjectId).get();
        Teacher teacher = teacherRepository.findById(teacherId).get();
        subject.assignTeacher(teacher);
        return subjectRepository.save(subject);
    }
}

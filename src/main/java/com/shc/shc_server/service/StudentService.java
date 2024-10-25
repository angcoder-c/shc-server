package com.shc.shc_server.service;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shc.shc_server.model.Activity;
import com.shc.shc_server.model.Student;
import com.shc.shc_server.repository.ActivityRepository;
import com.shc.shc_server.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ActivityRepository activityRepository;

    // get all
    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        students.forEach(student -> {
            Hibernate.initialize(student.getPreviousActivities());
            Hibernate.initialize(student.getPreferredActivities());
        });
        return students;
    }

    // get by email
    public Student findByEmail(String email) {
        Optional<Student> studentOpt = studentRepository.findByEmail(email);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            Hibernate.initialize(student.getPreviousActivities());
            Hibernate.initialize(student.getPreferredActivities());
            return student;
        }
        return null;
    }

    // get by id
    public Student getStudentById(Long id) {
        Optional<Student> studentOpt = studentRepository.findById(id);
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            Hibernate.initialize(student.getPreviousActivities());
            Hibernate.initialize(student.getPreferredActivities());
            return student;
        }
        return null;
    }

    // save a new student
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    // update student
    public Student updateStudent(Long id, Student updatedStudent) {
        Student existingStudent = getStudentById(id);
        if (existingStudent != null) {
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setPassword(updatedStudent.getPassword());
            existingStudent.setEmail(updatedStudent.getEmail());
            existingStudent.setMajor(updatedStudent.getMajor());
            existingStudent.setYear(updatedStudent.getYear());
            existingStudent.setScholarshipHours(updatedStudent.getScholarshipHours());
            existingStudent.setCompletedScholarshipHours(updatedStudent.getCompletedScholarshipHours());
            existingStudent.setPreviousActivities(updatedStudent.getPreviousActivities());
            existingStudent.setPreferredActivities(updatedStudent.getPreferredActivities());
            existingStudent.setAboutMe(updatedStudent.getAboutMe());
            existingStudent.setScore(updatedStudent.getScore());
            existingStudent.setNewActivity(updatedStudent.getNewActivity());
            existingStudent.setActivity(updatedStudent.getActivity());

            return studentRepository.save(existingStudent);
        }
        return null;
    }

    // delete student
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        }
    }

    // join activity
    public Student joinActivity(Long studentId, Long activityId) {
        Student student = getStudentById(studentId);
        Activity activity = activityRepository.findById(activityId)
                .orElse(null);

        if (activity != null && student != null) {
            if (activity.getStudents().size() < activity.getMaxCapacity()) {
                student.getPreferredActivities().add(activity);
                activity.getStudents().add(student);
                
                studentRepository.save(student);
                activityRepository.save(activity);
            }
        }

        return student;
    }

    // get completed scholarship hours
    public int getCompletedScholarshipHours(Long id) {
        Student student = getStudentById(id);
        if (student != null) {
            return (int) Math.round(student.getCompletedScholarshipHours());
        }
        return 0;
    }
}

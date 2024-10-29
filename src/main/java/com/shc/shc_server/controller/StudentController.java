package com.shc.shc_server.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shc.shc_server.model.Coordinator;
import com.shc.shc_server.model.Student;
import com.shc.shc_server.service.CoordinatorService;
import com.shc.shc_server.service.StudentService;
import com.shc.shc_server.service.TokenService;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CoordinatorService coordinatorService;

    @Autowired
    private TokenService tokenService;

    @GetMapping("/verify-role")
    public ResponseEntity<String> verifyUserRole(@RequestHeader("Authorization") String authorizationHeader) {
        String role = "";

        try {
            if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                String token = authorizationHeader.substring(7); // Extrae el token
                String email = tokenService.extractEmail(token);

                if (email != null) {
                    Student student = studentService.findByEmail(email);
                    Coordinator coordinator = coordinatorService.findByEmail(email);

                    if (student != null) {
                        role = "STUDENT";
                    } else if (coordinator != null) {
                        role = "COORDINATOR";
                    } else {
                        throw new Exception("No se encontró el usuario.");
                    }
                }
                return ResponseEntity.ok(role);
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token no proporcionado o inválido.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing token: " + e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/email/{email}")//ya
    public ResponseEntity<Student> getCoordinatorByEmail(@PathVariable String email) {
        Student student = studentService.findByEmail(email);
        return ResponseEntity.ok(student);
    }


    @PostMapping("/")
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.saveStudent(student);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{studentId}/join/{activityId}")
    public ResponseEntity<Student> joinActivity(@PathVariable Long studentId, @PathVariable Long activityId) {
        Student updatedStudent = studentService.joinActivity(studentId, activityId);
        return ResponseEntity.ok(updatedStudent);
    }

    @GetMapping("/{id}/completed-hours")
    public ResponseEntity<Integer> getCompletedScholarshipHours(@PathVariable Long id) {
        int hours = studentService.getCompletedScholarshipHours(id);
        return ResponseEntity.ok(hours);
    }

    @PutMapping("/{id}/updateRecord")
    public ResponseEntity<Student> updateStudentRecord(@PathVariable Long id, @RequestBody Student updatedStudent) {
        Student updatedRecord = studentService.updateStudent(id, updatedStudent);
        return ResponseEntity.ok(updatedRecord);
    }
}
package com.shc.shc_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shc.shc_server.dto.LoginDto;
import com.shc.shc_server.dto.RegisterCoordinatorDto;
import com.shc.shc_server.dto.RegisterDto;
import com.shc.shc_server.model.Coordinator;
import com.shc.shc_server.model.Student;
import com.shc.shc_server.service.CoordinatorService;
import com.shc.shc_server.service.StudentService;
import com.shc.shc_server.service.TokenService;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private CoordinatorService coordinatorService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/register/coordinator/")
    public ResponseEntity<String> registerCoordinator(@RequestBody RegisterCoordinatorDto registerDto) {
        Coordinator coordinator = new Coordinator();
        coordinator.setName(registerDto.getName());
        coordinator.setEmail(registerDto.getEmail());
        coordinator.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        coordinator.setPosition(registerDto.getPosition());

        coordinatorService.saveCoordinator(coordinator);
        return ResponseEntity.ok("Coordinator registered successfully");
    }

    @PostMapping("/login/coordinator/")
    public ResponseEntity<String> loginCoordinator(@RequestBody LoginDto loginDto) {
        Coordinator coordinator = coordinatorService.findByEmail(loginDto.getEmail());
        if (coordinator != null && passwordEncoder.matches(loginDto.getPassword(), coordinator.getPassword())) {
            String token = tokenService.generateTokenCoord(coordinator);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/register/student/")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        Student student = new Student();
        student.setName(registerDto.getName());
        student.setEmail(registerDto.getEmail());
        student.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        student.setMajor(registerDto.getMajor());
        student.setYear(registerDto.getYear());
        student.setAboutMe(registerDto.getAboutme());
        student.setScholarshipHours(registerDto.getScholarshipHours());

        student.setCompletedScholarshipHours(0.0);
        student.setPreviousActivities(null);
        student.setPreferredActivities(null);
        student.setScore(5.0);
        studentService.saveStudent(student);
        return ResponseEntity.ok("Student registered successfully");
    }

    @PostMapping("/login/student/")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        Student student = studentService.findByEmail(loginDto.getEmail());
        if (student != null && passwordEncoder.matches(loginDto.getPassword(), student.getPassword())) {
            String token = tokenService.generateToken(student);
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }
}
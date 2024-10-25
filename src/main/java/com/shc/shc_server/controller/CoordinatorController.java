
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
@RequestMapping("/api/coordinators")
public class CoordinatorController {

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
    public ResponseEntity<List<Coordinator>> getAllCoordinators() {
        List<Coordinator> coordinators = coordinatorService.getAllCoordinators();
        return ResponseEntity.ok(coordinators);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coordinator> getCoordinatorById(@PathVariable Long id) {
        Coordinator coordinator = coordinatorService.getCoordinatorById(id);
        return ResponseEntity.ok(coordinator);
    }

    @GetMapping("/email/{email}")//ya
    public ResponseEntity<Coordinator> getCoordinatorByEmail(@PathVariable String email) {
        Coordinator coordinator = coordinatorService.findByEmail(email);
        return ResponseEntity.ok(coordinator);
    }

    @PostMapping("/")
    public ResponseEntity<Coordinator> createCoordinator(@RequestBody Coordinator coordinator) {
        Coordinator createdCoordinator = coordinatorService.saveCoordinator(coordinator);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoordinator);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coordinator> updateCoordinator(@PathVariable Long id, @RequestBody Coordinator coordinator) {
        Coordinator updatedCoordinator = coordinatorService.updateCoordinator(id, coordinator);
        return ResponseEntity.ok(updatedCoordinator);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoordinator(@PathVariable Long id) {
        coordinatorService.deleteCoordinator(id);
        return ResponseEntity.noContent().build();
    }
}
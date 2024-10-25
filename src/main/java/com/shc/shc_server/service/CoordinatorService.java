package com.shc.shc_server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shc.shc_server.model.Coordinator;
import com.shc.shc_server.repository.CoordinatorRepository;

@Service
public class CoordinatorService {

    @Autowired
    private CoordinatorRepository coordinatorRepository;

    // Find coordinator by email
    public Coordinator findByEmail(String email) {
        return coordinatorRepository.findByEmail(email)
                .orElse(null);
    }

    // Get all coordinators
    public List<Coordinator> getAllCoordinators() {
        return coordinatorRepository.findAll();
    }

    // Get coordinator by ID
    public Coordinator getCoordinatorById(Long id) {
        return coordinatorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coordinator not found for id: " + id));
    }

    // Get coordinator by email
    public Coordinator getCoordinatorByEmail(String email) {
        return coordinatorRepository.findByEmail(email).orElse(null);
    }

    // Save a new coordinator
    public Coordinator saveCoordinator(Coordinator coordinator) {
        return coordinatorRepository.save(coordinator);
    }

    // Update existing coordinator
    public Coordinator updateCoordinator(Long id, Coordinator updatedCoordinator) {
        Coordinator existingCoordinator = getCoordinatorById(id);

        existingCoordinator.setName(updatedCoordinator.getName());
        existingCoordinator.setPassword(updatedCoordinator.getPassword());
        existingCoordinator.setEmail(updatedCoordinator.getEmail());
        existingCoordinator.setPosition(updatedCoordinator.getPosition());

        return coordinatorRepository.save(existingCoordinator);
    }

    // Delete coordinator
    public void deleteCoordinator(Long id) {
        if (!coordinatorRepository.existsById(id)) {
            throw new RuntimeException("Coordinator not found for id: " + id);
        }
        coordinatorRepository.deleteById(id);
    }
}
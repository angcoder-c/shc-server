package com.shc.shc_server.repository;

import com.shc.shc_server.model.Coordinator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoordinatorRepository extends JpaRepository<Coordinator, Long> {

    // buscar por id
    default Coordinator getById(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Coordinator not found with id: " + id));
    }

    // buscar por correo electrónico
    Optional<Coordinator> findByEmail(String email);

    // buscar por nombre
    Optional<Coordinator> findByNameContainingIgnoreCase(String name);
}

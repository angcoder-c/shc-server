package com.shc.shc_server.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shc.shc_server.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    // buscar actividad por id
    default Activity getById(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("not found id: " + id));
    }

    // buscar por nombre
    List<Activity> findByNameContainingIgnoreCase(String name);

    // buscar actividades por coordinador
    List<Activity> findByCoordinatorContainingIgnoreCase(String coordinator);

    // buscar por fecha
    List<Activity> findByDate(Date date);

    // buscar por departamento
    List<Activity> findByDepartmentContainingIgnoreCase(String department);

    // buscar actividades que tengan una capacidad máxima mayor o igual a un valor dado
    List<Activity> findByMaxCapacityGreaterThanEqual(int capacity);

    // buscar actividades que tienen un multiplicador mayor que un valor dado
    List<Activity> findByMultiplierGreaterThan(Double multiplier);
}

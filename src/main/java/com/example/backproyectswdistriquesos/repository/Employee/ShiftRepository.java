package com.example.backproyectswdistriquesos.repository.Employee;

import com.example.backproyectswdistriquesos.models.Employee.Shift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    List<Shift> findByClientClientId(Long clientId);
}

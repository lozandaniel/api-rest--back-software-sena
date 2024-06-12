package com.example.backproyectswdistriquesos.services;

import com.example.backproyectswdistriquesos.models.Employee.Shift;
import com.example.backproyectswdistriquesos.repository.Employee.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShiftService {
    @Autowired
    private ShiftRepository shiftRepository;

    public List<Shift> getShiftsOfEmployeeId(Long clientId) {
        return shiftRepository.findByClientClientId(clientId);
    }

    public Shift createShiftEmployee(Shift shift) {
        return shiftRepository.save(shift);
    }
}

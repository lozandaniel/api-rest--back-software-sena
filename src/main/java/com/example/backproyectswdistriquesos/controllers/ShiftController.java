package com.example.backproyectswdistriquesos.controllers;

import com.example.backproyectswdistriquesos.models.Employee.Shift;
import com.example.backproyectswdistriquesos.services.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/shifts")
public class ShiftController {
    @Autowired
    private ShiftService shiftService;

    @GetMapping("/{clientId}")
    public List<Shift> getShiftsByEmployee(@PathVariable Long clientId) {
        return shiftService.getShiftsOfEmployeeId(clientId);
    }

    @PostMapping
    public Shift createShiftToEmployee(@RequestBody Shift shift) {
        return shiftService.createShiftEmployee(shift);
    }
}

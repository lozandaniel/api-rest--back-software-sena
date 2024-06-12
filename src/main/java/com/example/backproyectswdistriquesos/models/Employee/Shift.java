package com.example.backproyectswdistriquesos.models.Employee;

import com.example.backproyectswdistriquesos.models.Client;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "turnos")
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
    private Long shiftId;

    @ManyToOne
    @JoinColumn(name = "id_empleado", referencedColumnName = "id_cliente")
    private Client client;

    @Column(name = "fecha_turno")
    private LocalDateTime shiftDate;

    @Column(name = "hora_inicio")
    private String startTime;

    @Column(name = "hora_final")
    private String endTime;

    public Shift() {
    }

    public Shift(Long shiftId, Client client, LocalDateTime shiftDate, String startTime, String endTime) {
        this.shiftId = shiftId;
        this.client = client;
        this.shiftDate = shiftDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getShiftId() {
        return shiftId;
    }

    public void setShiftId(Long shiftId) {
        this.shiftId = shiftId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public LocalDateTime getShiftDate() {
        return shiftDate;
    }

    public void setShiftDate(LocalDateTime shiftDate) {
        this.shiftDate = shiftDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}

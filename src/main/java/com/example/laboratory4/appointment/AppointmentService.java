package com.example.laboratory4.appointment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    public Appointment findAppointmentById(String id) {
        return appointmentRepository.findById(id).orElse(null);
    }

    public List<Appointment> listAppointments(int option, String search) throws Exception {
        return appointmentRepository.listAppointments(option, search);
    }

    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }

    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }

    public void modifyAppointment(Appointment appointment) throws Exception {
        appointmentRepository.modifyAppointment(appointment);
    }
}

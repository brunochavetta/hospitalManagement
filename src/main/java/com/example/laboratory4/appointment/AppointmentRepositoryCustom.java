package com.example.laboratory4.appointment;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepositoryCustom {
    void modifyAppointment(Appointment appointment) throws Exception;
    List<Appointment> listAppointments(int option, String search) throws Exception;
}

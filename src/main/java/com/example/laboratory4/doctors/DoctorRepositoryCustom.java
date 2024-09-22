package com.example.laboratory4.doctors;

import java.util.List; 

public interface DoctorRepositoryCustom {
    void modifyDoctor(Doctor doctor) throws Exception;
    List<Doctor> listDoctors(int option, String search) throws Exception;
}

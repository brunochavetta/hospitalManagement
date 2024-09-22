package com.example.laboratory4.doctors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    public Doctor findDoctorById(String id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public List<Doctor> listDoctors(int option, String search) throws Exception {
        return doctorRepository.listDoctors(option, search);
    }

    public void deleteDoctor(String id) {
        doctorRepository.deleteById(id);
    }

    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }

    public void modifyDoctor(Doctor doctor) throws Exception {
        doctorRepository.modifyDoctor(doctor);
    }
}

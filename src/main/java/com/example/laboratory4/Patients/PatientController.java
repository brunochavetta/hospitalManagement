package com.example.laboratory4.Patients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listPatients(@RequestParam(value = "option", defaultValue = "11") int option,
            @RequestParam(value = "search", defaultValue = "") String search,
            Model model) {
        try {
            List<Patient> patients;
            if (option == 11) {
                patients = patientService.findAll();
            } else {
                patients = patientService.listPatients(option, search);
            }
            model.addAttribute("patients", patients);
            model.addAttribute("filterOption", option);
            model.addAttribute("searchTerm", search);
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching patients: " + e.getMessage());
        }
        return "patient-list";
    }

    @GetMapping("/new")
    public String showAddPatientForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add-patient";
    }

    @PostMapping("/save")
    public String savePatient(@ModelAttribute("patient") Patient patient) {
        try {
            patientService.savePatient(patient);
        } catch (Exception e) {
            // Manejo de excepciones
            return "redirect:/patients?error=" + e.getMessage();
        }
        return "redirect:/patients";
    }

    @GetMapping("/{id}")
    public String showPatientDetails(@PathVariable("id") String id, Model model) {
        try {
            Patient patient = patientService.findPatientById(id);
            model.addAttribute("patient", patient);
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching patient details: " + e.getMessage());
        }
        return "patient-details";
    }

    @GetMapping("/delete/{id}")
    public String deletePatient(@PathVariable("id") String id) {
        try {
            patientService.deletePatient(id);
        } catch (Exception e) {
            return "redirect:/patients?error=" + e.getMessage();
        }
        return "redirect:/patients";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") String id,
            @RequestParam(value = "option", defaultValue = "1") int option, Model model) {
        try {
            Patient patient = patientService.findPatientById(id);
            if (patient == null) {
                throw new Exception("Patient not found with ID: " + id);
            }
            model.addAttribute("patient", patient);
            model.addAttribute("option", option);
            return "edit-patient";
        } catch (Exception e) {
            model.addAttribute("error", "Error loading patient: " + e.getMessage());
            return "error";
        }
    }

    @PostMapping("/edit")
    public String editPatient(@ModelAttribute Patient patient, Model model) {
        try {
            patientService.modifyPatient(patient);
            return "redirect:/patients";
        } catch (Exception e) {
            model.addAttribute("error", "Error modifying patient: " + e.getMessage());
            return "edit-patient";
        }
    }
}

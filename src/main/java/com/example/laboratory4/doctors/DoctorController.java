package com.example.laboratory4.doctors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping
    public String listDoctors(@RequestParam(value = "option", defaultValue = "8") int option,
            @RequestParam(value = "search", defaultValue = "") String search,
            Model model) {
        try {
            List<Doctor> doctors;
            if (option == 8) {
                doctors = doctorService.findAll();
            } else {
                doctors = doctorService.listDoctors(option, search);
            }
            model.addAttribute("doctors", doctors);
            model.addAttribute("filterOption", option);
            model.addAttribute("searchTerm", search);
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching doctors: " + e.getMessage());
        }
        return "doctor-list";
    }

    @GetMapping("/new")
    public String showAddDoctorForm(Model model) {
        model.addAttribute("doctor", new Doctor());
        return "add-doctor";
    }

    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {
        try {
            doctorService.saveDoctor(doctor);
        } catch (Exception e) {
            // Manejo de excepciones
            return "redirect:/doctors?error=" + e.getMessage();
        }
        return "redirect:/doctors";
    }

    @GetMapping("/{id}")
    public String showDoctorDetails(@PathVariable("id") String id, Model model) {
        try {
            Doctor doctor = doctorService.findDoctorById(id);
            model.addAttribute("doctor", doctor);
        } catch (Exception e) {
            // Manejo de excepciones
            model.addAttribute("error", "Error fetching doctor details: " + e.getMessage());
        }
        return "doctor-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditDoctorForm(@PathVariable("id") String id, Model model) {
        try {
            Doctor doctor = doctorService.findDoctorById(id);
            if (doctor == null) {
                return "redirect:/doctors?error=Doctor not found.";
            }
            model.addAttribute("doctor", doctor);
        } catch (Exception e) {
            return "redirect:/doctors?error=" + e.getMessage();
        }
        return "edit-doctor";
    }

    @PostMapping("/update")
    public String updateDoctor(@ModelAttribute("doctor") Doctor doctor) {
        try {
            doctorService.modifyDoctor(doctor);
        } catch (Exception e) {
            return "redirect:/doctors?error=" + e.getMessage();
        }
        return "redirect:/doctors";
    }

    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable("id") String id) {
        try {
            doctorService.deleteDoctor(id);
        } catch (Exception e) {
            return "redirect:/doctors?error=" + e.getMessage();
        }
        return "redirect:/doctors";
    }

}
package com.example.laboratory4.medication;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/medications")
public class MedicationController {

    @Autowired
    private MedicationService medicationService;

    @GetMapping
    public String listMedications(@RequestParam(value = "option", defaultValue = "7") int option,
            @RequestParam(value = "search", defaultValue = "") String search,
            Model model) {
        try {
            List<Medication> medications;
            if (option == 7) {
                medications = medicationService.findAll();
            } else {
                medications = medicationService.listMedications(option, search);
            }
            model.addAttribute("medications", medications);
            model.addAttribute("filterOption", option);
            model.addAttribute("searchTerm", search);
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching medications: " + e.getMessage());
        }
        return "medication-list";
    }

    @GetMapping("/new")
    public String showAddMedicationForm(Model model) {
        model.addAttribute("medication", new Medication());
        return "add-medication";
    }

    @PostMapping("/save")
    public String saveMedication(@ModelAttribute("medication") Medication medication) {
        try {
            medicationService.saveMedication(medication);
        } catch (Exception e) {
            // Manejo de excepciones
            return "redirect:/medications?error=" + e.getMessage();
        }
        return "redirect:/medications";
    }

    @GetMapping("/{id}")
    public String showMedicationDetails(@PathVariable("id") String id, Model model) {
        try {
            Medication medication = medicationService.findMedicationById(id);
            model.addAttribute("medication", medication);
        } catch (Exception e) {
            // Manejo de excepciones
            model.addAttribute("error", "Error fetching medication details: " + e.getMessage());
        }
        return "medication-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditMedicationForm(@PathVariable("id") String id, Model model) {
        try {
            Medication medication = medicationService.findMedicationById(id);
            if (medication == null) {
                return "redirect:/medications?error=medication not found.";
            }
            model.addAttribute("medication", medication);
        } catch (Exception e) {
            return "redirect:/medications?error=" + e.getMessage();
        }
        return "edit-medication";
    }

    @PostMapping("/update")
    public String updateMedication(@ModelAttribute("medication") Medication medication) {
        try {
            medicationService.modifyMedication(medication);
        } catch (Exception e) {
            return "redirect:/medications?error=" + e.getMessage();
        }
        return "redirect:/medications";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedication(@PathVariable("id") String id) {
        try {
            medicationService.deleteMedication(id);
        } catch (Exception e) {
            return "redirect:/medications?error=" + e.getMessage();
        }
        return "redirect:/medications";
    }
}

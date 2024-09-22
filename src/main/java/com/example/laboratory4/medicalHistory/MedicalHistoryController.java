package com.example.laboratory4.medicalHistory;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.laboratory4.Patients.Patient;
import com.example.laboratory4.Patients.PatientService;

@Controller
@RequestMapping("/medicalHistorys")
public class MedicalHistoryController {

    @Autowired
    private MedicalHistoryService medicalHistoryService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listMedicalHistorys(
            @RequestParam(value = "option", defaultValue = "8") int option,
            @RequestParam(value = "search", defaultValue = "") String search,
            Model model) {
        try {
            System.out.println("Valor enviado" + search);
            List<MedicalHistory> medicalHistorys;
            if (option == 8) {
                medicalHistorys = medicalHistoryService.findAll();
            } else {
                medicalHistorys = medicalHistoryService.listmedicalHistorys(option, search);
            }
            model.addAttribute("medicalHistorys", medicalHistorys);
            model.addAttribute("filterOption", option);
            model.addAttribute("searchTerm", search);
            model.addAttribute("patients", patientService.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching medicalHistorys: " + e.getMessage());
        }
        return "medicalHistory-list";
    }

    @GetMapping("/search/patients")
    @ResponseBody
    public List<Patient> searchPatients(@RequestParam("option") int option,
            @RequestParam("search") String search) throws Exception {
        return patientService.listPatients(option, search);
    }

    @GetMapping("/new")
    public String showAddMedicalHistoryForm(Model model) {
        model.addAttribute("medicalHistory", new MedicalHistory());
        model.addAttribute("patients", patientService.findAll());
        return "add-medicalHistory";
    }

    @PostMapping("/save")
    public String saveMedicalHistory(@ModelAttribute("medicalHistory") MedicalHistory medicalHistory) {
        try {
            medicalHistoryService.savemedicalHistory(medicalHistory);
        } catch (Exception e) {
            return "redirect:/medicalHistorys?error=" + e.getMessage();
        }
        return "redirect:/medicalHistorys";
    }

    @GetMapping("/{id}")
    public String showMedicalHistoryDetails(@PathVariable("id") String id, Model model) {
        try {
            MedicalHistory medicalHistory = medicalHistoryService.findmedicalHistoryById(id);
            model.addAttribute("medicalHistory", medicalHistory);
            model.addAttribute("patients", patientService.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching medicalHistory details: " + e.getMessage());
        }
        return "medicalHistory-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditMedicalHistoryForm(@PathVariable("id") String id, Model model) {
        try {
            MedicalHistory medicalHistory = medicalHistoryService.findmedicalHistoryById(id);
            if (medicalHistory == null) {
                return "redirect:/medicalHistorys?error=medicalHistory not found.";
            }
            model.addAttribute("medicalHistory", medicalHistory);
            model.addAttribute("patients", patientService.findAll());
        } catch (Exception e) {
            return "redirect:/medicalHistorys?error=" + e.getMessage();
        }
        return "edit-medicalHistory";
    }

    @PostMapping("/update")
    public String updateMedicalHistory(@ModelAttribute("medicalHistory") MedicalHistory medicalHistory) {
        try {
            medicalHistoryService.modifymedicalHistory(medicalHistory);
        } catch (Exception e) {
            return "redirect:/medicalHistorys?error=" + e.getMessage();
        }
        return "redirect:/medicalHistorys";
    }

    @GetMapping("/delete/{id}")
    public String deleteMedicalHistory(@PathVariable("id") String id) {
        try {
            medicalHistoryService.deletemedicalHistory(id);
        } catch (Exception e) {
            return "redirect:/medicalHistorys?error=" + e.getMessage();
        }
        return "redirect:/medicalHistorys";
    }
}

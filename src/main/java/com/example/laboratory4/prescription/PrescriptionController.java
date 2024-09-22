package com.example.laboratory4.prescription;

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
import com.example.laboratory4.doctors.Doctor;
import com.example.laboratory4.doctors.DoctorService;
import com.example.laboratory4.medication.Medication;
import com.example.laboratory4.medication.MedicationService;

@Controller
@RequestMapping("/prescriptions")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedicationService medicationService;

    @GetMapping
    public String listPrescriptions(
            @RequestParam(value = "option", defaultValue = "9") int option,
            @RequestParam(value = "search", defaultValue = "") String search,
            Model model) {
        try {
            System.out.println("Valor enviado" + search);
            List<Prescription> prescriptions;
            if (option == 9) {
                prescriptions = prescriptionService.findAll();
            } else {
                prescriptions = prescriptionService.listPrescriptions(option, search);
            }
            model.addAttribute("prescriptions", prescriptions);
            model.addAttribute("filterOption", option);
            model.addAttribute("searchTerm", search);
            model.addAttribute("patients", patientService.findAll());
            model.addAttribute("doctors", doctorService.findAll());
            model.addAttribute("medications", medicationService.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching prescriptions: " + e.getMessage());
        }
        return "prescription-list";
    }

    @GetMapping("/search/patients")
    @ResponseBody
    public List<Patient> searchPatients(@RequestParam("option") int option,
            @RequestParam("search") String search) throws Exception {
        return patientService.listPatients(option, search);
    }

    @GetMapping("/search/doctors")
    @ResponseBody
    public List<Doctor> searchDoctors(@RequestParam("option") int option,
            @RequestParam("search") String search) throws Exception {
        return doctorService.listDoctors(option, search);
    }

    @GetMapping("/search/medications")
    @ResponseBody
    public List<Medication> searchMedications(@RequestParam("option") int option,
            @RequestParam("search") String search) throws Exception {
        return medicationService.listMedications(option, search);
    }

    @GetMapping("/new")
    public String showAddPrescriptionForm(Model model) {
        model.addAttribute("prescription", new Prescription());
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("patients", patientService.findAll());
        model.addAttribute("medications", medicationService.findAll());
        return "add-prescription";
    }

    @PostMapping("/save")
    public String saveprescription(@ModelAttribute("prescription") Prescription prescription) {
        try {
            prescriptionService.savePrescription(prescription);
        } catch (Exception e) {
            return "redirect:/prescriptions?error=" + e.getMessage();
        }
        return "redirect:/prescriptions";
    }

    @GetMapping("/{id}")
    public String showPrescriptionDetails(@PathVariable("id") String id, Model model) {
        try {
            Prescription prescription = prescriptionService.findPrescriptionById(id);
            model.addAttribute("prescription", prescription);
            model.addAttribute("doctors", doctorService.findAll());
            model.addAttribute("patients", patientService.findAll());
            model.addAttribute("medications", medicationService.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching prescription details: " + e.getMessage());
        }
        return "prescription-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditPrescriptionForm(@PathVariable("id") String id, Model model) {
        try {
            Prescription prescription = prescriptionService.findPrescriptionById(id);
            if (prescription == null) {
                return "redirect:/prescriptions?error=prescription not found.";
            }
            model.addAttribute("prescription", prescription);
            model.addAttribute("doctors", doctorService.findAll());
            model.addAttribute("patients", patientService.findAll());
            model.addAttribute("medications", medicationService.findAll());
        } catch (Exception e) {
            return "redirect:/prescriptions?error=" + e.getMessage();
        }
        return "edit-prescription";
    }

    @PostMapping("/update")
    public String updatePrescription(@ModelAttribute("prescription") Prescription prescription) {
        try {
            prescriptionService.modifyPrescription(prescription);
        } catch (Exception e) {
            return "redirect:/prescriptions?error=" + e.getMessage();
        }
        return "redirect:/prescriptions";
    }

    @GetMapping("/delete/{id}")
    public String deletePrescription(@PathVariable("id") String id) {
        try {
            prescriptionService.deletePrescription(id);
        } catch (Exception e) {
            return "redirect:/prescriptions?error=" + e.getMessage();
        }
        return "redirect:/prescriptions";
    }
}

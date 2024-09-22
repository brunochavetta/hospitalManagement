package com.example.laboratory4.appointment;

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

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listAppointments(
            @RequestParam(value = "option", defaultValue = "8") int option,
            @RequestParam(value = "search", defaultValue = "") String search,
            Model model) {
        try {
            System.out.println("Valor enviado" + search);
            List<Appointment> appointments;
            if (option == 8) {
                appointments = appointmentService.findAll();
            } else {
                appointments = appointmentService.listAppointments(option, search);
            }
            model.addAttribute("appointments", appointments);
            model.addAttribute("filterOption", option);
            model.addAttribute("searchTerm", search);
            model.addAttribute("patients", patientService.findAll());
            model.addAttribute("doctors", doctorService.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching appointments: " + e.getMessage());
        }
        return "appointment-list";
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

    @GetMapping("/new")
    public String showAddAppointmentForm(Model model) {
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("doctors", doctorService.findAll());
        model.addAttribute("patients", patientService.findAll());
        return "add-appointment";
    }

    @PostMapping("/save")
    public String saveAppointment(@ModelAttribute("appointment") Appointment appointment) {
        try {
            appointmentService.saveAppointment(appointment);
        } catch (Exception e) {
            return "redirect:/appointments?error=" + e.getMessage();
        }
        return "redirect:/appointments";
    }

    @GetMapping("/{id}")
    public String showAppointmentDetails(@PathVariable("id") String id, Model model) {
        try {
            Appointment appointment = appointmentService.findAppointmentById(id);
            model.addAttribute("appointment", appointment);
            model.addAttribute("doctors", doctorService.findAll());
            model.addAttribute("patients", patientService.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching appointment details: " + e.getMessage());
        }
        return "appointment-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditAppointmentForm(@PathVariable("id") String id, Model model) {
        try {
            Appointment appointment = appointmentService.findAppointmentById(id);
            if (appointment == null) {
                return "redirect:/appointments?error=appointment not found.";
            }
            model.addAttribute("appointment", appointment);
            model.addAttribute("doctors", doctorService.findAll());
            model.addAttribute("patients", patientService.findAll());
        } catch (Exception e) {
            return "redirect:/appointments?error=" + e.getMessage();
        }
        return "edit-appointment";
    }

    @PostMapping("/update")
    public String updateAppointment(@ModelAttribute("appointment") Appointment appointment) {
        try {
            appointmentService.modifyAppointment(appointment);
        } catch (Exception e) {
            return "redirect:/appointments?error=" + e.getMessage();
        }
        return "redirect:/appointments";
    }

    @GetMapping("/delete/{id}")
    public String deleteAppointment(@PathVariable("id") String id) {
        try {
            appointmentService.deleteAppointment(id);
        } catch (Exception e) {
            return "redirect:/appointments?error=" + e.getMessage();
        }
        return "redirect:/appointments";
    }
}

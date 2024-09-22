package com.example.laboratory4.test;

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
@RequestMapping("/tests")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private PatientService patientService;

    @GetMapping
    public String listTests(
            @RequestParam(value = "option", defaultValue = "7") int option,
            @RequestParam(value = "search", defaultValue = "") String search,
            Model model) {
        try {
            List<Test> tests;
            if (option == 7) {
                tests = testService.findAll();
            } else {
                tests = testService.listTests(option, search);
            }
            model.addAttribute("tests", tests);
            model.addAttribute("filterOption", option);
            model.addAttribute("searchTerm", search);
            model.addAttribute("patients", patientService.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching tests: " + e.getMessage());
        }
        return "test-list";
    }

    @GetMapping("/search/patients")
    @ResponseBody
    public List<Patient> searchPatients(@RequestParam("option") int option,
            @RequestParam("search") String search) throws Exception {
        return patientService.listPatients(option, search);
    }

    @GetMapping("/new")
    public String showAddTestForm(Model model) {
        Test test = new Test();
        model.addAttribute("test", test);
        model.addAttribute("patients", patientService.findAll());
        return "add-test";
    }

    @PostMapping("/save")
    public String saveTest(@ModelAttribute("test") Test test) {
        try {
            testService.saveTest(test);
        } catch (Exception e) {
            return "redirect:/tests?error=" + e.getMessage();
        }
        return "redirect:/tests";
    }

    @GetMapping("/{id}")
    public String showTestDetails(@PathVariable("id") String id, Model model) {
        try {
            Test test = testService.findTestById(id);
            model.addAttribute("test", test);
            model.addAttribute("patients", patientService.findAll());
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching test details: " + e.getMessage());
        }
        return "test-details";
    }

    @GetMapping("/edit/{id}")
    public String showEditTestForm(@PathVariable("id") String id, Model model) {
        try {
            Test test = testService.findTestById(id);
            if (test == null) {
                return "redirect:/tests?error=test not found.";
            }
            model.addAttribute("test", test);
            model.addAttribute("patients", patientService.findAll());
        } catch (Exception e) {
            return "redirect:/tests?error=" + e.getMessage();
        }
        return "edit-test";
    }

    @PostMapping("/update")
    public String updateTest(@ModelAttribute("test") Test test) {
        try {
            testService.modifyTest(test);
        } catch (Exception e) {
            return "redirect:/tests?error=" + e.getMessage();
        }
        return "redirect:/tests";
    }

    @GetMapping("/delete/{id}")
    public String deleteTest(@PathVariable("id") String id) {
        try {
            testService.deleteTest(id);
        } catch (Exception e) {
            return "redirect:/tests?error=" + e.getMessage();
        }
        return "redirect:/tests";
    }
}

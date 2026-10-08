package DBMS.MINI.Project.demo.Controller;

import DBMS.MINI.Project.demo.Service.PatientService;
import DBMS.MINI.Project.demo.entity.patientlogin.patient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientWebController {

    private final PatientService patientService;

    // Show all patients
    @GetMapping
    public String getAllPatients(Model model) {

        model.addAttribute(
                "patients",
                patientService.getAllPatients()
        );

        return "patients/list";
    }

    // Show registration form
    @GetMapping("/new")
    public String showCreateForm(Model model) {

        model.addAttribute("patient", new patient());

        return "patients/form";
    }

    // Save patient
    @PostMapping("/save")
    public String createPatient(
            @Valid @ModelAttribute("patient") patient patient,
            BindingResult result) {

        if (result.hasErrors()) {
            return "patients/form";
        }

        patientService.createPatient(patient);

        return "redirect:/patients";
    }

    // Show patient details
    @GetMapping("/{id}")
    public String getPatientById(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "patient",
                patientService.getPatientById(id)
        );

        return "patients/view";
    }

    // Show edit form
    @GetMapping("/{id}/edit")
    public String editPatient(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "patient",
                patientService.getPatientById(id)
        );

        return "patients/form";
    }

    // Update patient
    @PostMapping("/{id}/update")
    public String updatePatient(
            @PathVariable Long id,
            @Valid @ModelAttribute("patient") patient patient,
            BindingResult result) {

        if (result.hasErrors()) {
            return "patients/form";
        }

        patientService.updatePatient(id, patient);

        return "redirect:/patients";
    }

    // Delete / deactivate patient
    @PostMapping("/{id}/delete")
    public String deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        return "redirect:/patients";
    }

    // Search patient
    @GetMapping("/search")
    public String searchPatients(
            @RequestParam String name,
            Model model) {

        model.addAttribute(
                "patients",
                patientService.searchByFirstName(name)
        );

        return "patients/list";
    }
}
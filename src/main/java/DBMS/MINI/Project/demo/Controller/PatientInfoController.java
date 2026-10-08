package DBMS.MINI.Project.demo.Controller;

import DBMS.MINI.Project.demo.Service.PatientInfoService;
import DBMS.MINI.Project.demo.entity.lab.PatientInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lab/patient")
@RequiredArgsConstructor
public class PatientInfoController {

    private final PatientInfoService patientInfoService;



    @GetMapping("/register")
    public String patientRegister(Model model) {

        model.addAttribute(
                "patients",
                patientInfoService.getAllPatients()
        );

        return "patients/register";
    }
    // ==========================================
    // SHOW PATIENT FORM
    // ==========================================

    @GetMapping("/new")
    public String showPatientForm(Model model) {

        model.addAttribute(
                "patientInfo",
                new PatientInfo()
        );

        return "patients/create";
    }

    // ==========================================
    // SAVE PATIENT
    // ==========================================

    @PostMapping("/save")
    public String savePatient(
            @ModelAttribute("patientInfo")
            PatientInfo patientInfo) {

        PatientInfo savedPatient =
                patientInfoService.createPatient(patientInfo);
return "redirect:/lab/patient/"
        + savedPatient.getId()
        + "/tests";
    }

    // ==========================================
    // VIEW PATIENT
    // ==========================================

    @GetMapping("/{id}")
    public String viewPatient(
            @PathVariable Long id,
            Model model) {

        PatientInfo patientInfo =
                patientInfoService.getPatientById(id);

        model.addAttribute(
                "patientInfo",
                patientInfo
        );

        return "lab/patient/view";
    }
}
package DBMS.MINI.Project.demo.Controller;

import DBMS.MINI.Project.demo.entity.patientlogin.patient;
import DBMS.MINI.Project.demo.repository.PatientRepository;
import DBMS.MINI.Project.demo.repository.labrepo.PatientInfoRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PatientDashboardController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PatientInfoRepository patientInfoRepository;

    @GetMapping("/patients/dashboard")
    public String dashboard(HttpSession session, Model model) {

        // Get the logged-in patient's ID
        Long patientId =
                (Long) session.getAttribute("loggedInPatientId");

        // Redirect if the patient is not logged in
        if (patientId == null) {
            return "redirect:/patients/login";
        }

        // Fetch the login/profile entity
        patient loggedInPatient =
                patientRepository.findById(patientId)
                        .orElseThrow(() ->
                                new RuntimeException("Patient not found.")
                        );

        // Send login/profile details to Thymeleaf
        model.addAttribute("patient", loggedInPatient);

        // Find pathology billing information using the patient's email
        model.addAttribute(
                "patientInfo",
                patientInfoRepository
                        .findByEmail(loggedInPatient.getEmail())
                        .orElse(null)
        );

        return "patients/dashboard";
    }
}
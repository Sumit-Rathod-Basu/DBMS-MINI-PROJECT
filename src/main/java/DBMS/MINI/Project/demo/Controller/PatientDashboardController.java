package DBMS.MINI.Project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import DBMS.MINI.Project.demo.entity.patientlogin.patient;
import DBMS.MINI.Project.demo.repository.PatientRepository;
import jakarta.servlet.http.HttpSession;

@Controller
public class PatientDashboardController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping("/patients/dashboard")
    public String dashboard(
            HttpSession session,
            Model model) {

        Long patientId =
                (Long) session.getAttribute("loggedInPatientId");

        // Patient not logged in
        if (patientId == null) {
            return "redirect:/patients/login";
        }

        patient loggedInPatient =
                patientRepository.findById(patientId)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Patient not found."
                                )
                        );
        model.addAttribute(
                "patient",
                loggedInPatient
        );

        return "patients/dashboard";
    }
}
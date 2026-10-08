package DBMS.MINI.Project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import DBMS.MINI.Project.demo.Service.PatientAuthenticationService;
import DBMS.MINI.Project.demo.entity.patientlogin.patient;
import jakarta.servlet.http.HttpSession;

@Controller
public class PatientAuthenticationController {

    @Autowired
    private PatientAuthenticationService authenticationService;


    // ==========================================
    // SHOW SIGNUP PAGE
    // ==========================================

    @GetMapping("/patients/signup")
    public String showSignupPage() {

        return "patients/signup";
    }


    // ==========================================
    // PATIENT SIGNUP
    // ==========================================

    @PostMapping("/patients/signup")
    public String signup(
            @RequestParam String email,
            @RequestParam String password,
            @RequestParam String confirmPassword,
            Model model) {

        try {

            authenticationService.signup(
                    email,
                    password,
                    confirmPassword
            );

            // Signup successful
            return "redirect:/patients/login?signupSuccess=true";

        } catch (RuntimeException e) {

            // Show error on signup page
            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "patients/signup";
        }
    }


    // ==========================================
    // SHOW LOGIN PAGE
    // ==========================================

    @GetMapping("/patients/login")
    public String showLoginPage(
            @RequestParam(required = false) String signupSuccess,
            Model model) {

        if ("true".equals(signupSuccess)) {

            model.addAttribute(
                    "success",
                    "Account created successfully. Please login."
            );
        }

        return "patients/login";
    }


    // ==========================================
    // PATIENT LOGIN
    // ==========================================

    @PostMapping("/patients/login")
    public String login(
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session,
            Model model) {

        try {

            patient loggedInPatient =
                    authenticationService.login(
                            email,
                            password
                    );

            // Store patient ID in session
            session.setAttribute(
                    "loggedInPatientId",
                    loggedInPatient.getPatientId()
            );

            // Store patient email in session
            session.setAttribute(
                    "loggedInPatientEmail",
                    loggedInPatient.getEmail()
            );

            // Login successful
          return "redirect:/patients/dashboard";

        } catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "patients/login";
        }
    }


    // ==========================================
    // PATIENT LOGOUT
    // ==========================================

    @GetMapping("/patient/logout")
    public String logout(HttpSession session) {

        // Destroy session
        session.invalidate();

        // Go back to login page
        return "redirect:/patients/login?logout=true";
    }
}
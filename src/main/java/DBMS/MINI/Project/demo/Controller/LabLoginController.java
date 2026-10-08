package DBMS.MINI.Project.demo.Controller;

import DBMS.MINI.Project.demo.Service.labservice.LabLoginService;
import DBMS.MINI.Project.demo.entity.lab.LabAssistant;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lab")
public class LabLoginController {


    @Autowired
    private LabLoginService labLoginService;


    // =========================
    // REGISTER PAGE
    // =========================

    @GetMapping("/register")
    public String registerPage() {

        return "lab/register";
    }


    // =========================
    // REGISTER
    // =========================

    @PostMapping("/register")
    public String register(

            @RequestParam String fullName,

            @RequestParam String email,

            @RequestParam String mobile,

            @RequestParam String password,

            Model model) {

        try {

            labLoginService.register(
                    fullName,
                    email,
                    mobile,
                    password
            );

            return "redirect:/lab/login?registered=true";

        } catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    e.getMessage()
            );

            return "lab/register";
        }
    }


    // =========================
    // LOGIN PAGE
    // =========================

    @GetMapping("/login")
    public String loginPage(
            @RequestParam(required = false)
            String registered,

            Model model) {

        if ("true".equals(registered)) {

            model.addAttribute(
                    "success",
                    "Registration successful! Please login."
            );
        }

        return "lab/login";
    }


    // =========================
    // LOGIN
    // =========================

    @PostMapping("/login")
    public String login(

            @RequestParam String email,

            @RequestParam String password,

            HttpSession session,

            Model model) {


        LabAssistant assistant =
                labLoginService.login(
                        email,
                        password
                );


        // Login failed
        if (assistant == null) {

            model.addAttribute(
                    "error",
                    "Invalid email or password"
            );

            return "lab/login";
        }


        // =========================
        // CREATE SESSION
        // =========================

        session.setAttribute(
                "labAssistantId",
                assistant.getAssistantId()
        );

        session.setAttribute(
                "labAssistantName",
                assistant.getFullName()
        );

        session.setAttribute(
                "labAssistantEmail",
                assistant.getEmail()
        );


        // Login successful
        return "redirect:/lab/dashboard";
    }


    // =========================
    // LOGOUT
    // =========================

    @GetMapping("/logout")
    public String logout(
            HttpSession session) {

        session.invalidate();

        return "redirect:/lab/login";
    }
}
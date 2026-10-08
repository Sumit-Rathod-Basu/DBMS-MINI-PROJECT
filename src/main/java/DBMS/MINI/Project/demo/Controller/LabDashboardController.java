package DBMS.MINI.Project.demo.Controller;


import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import DBMS.MINI.Project.demo.Service.LabReportService;
import DBMS.MINI.Project.demo.Service.labservice.LabOrderService;
import DBMS.MINI.Project.demo.Service.labservice.LabTestService;
import DBMS.MINI.Project.demo.Service.labservice.SampleService;

@Controller
@RequestMapping("/lab")
public class LabDashboardController {

    @Autowired
    private LabOrderService labOrderService;

    @Autowired
    private LabTestService labTestService;

    @Autowired
    private SampleService sampleService;

    @Autowired
    private LabReportService labReportService;


    @GetMapping("/dashboard")
    public String dashboard(
            HttpSession session,
            Model model) {

        // Check login
        if (session.getAttribute("labAssistantId") == null) {

            return "redirect:/lab/login";
        }


        // Logged-in assistant information
        model.addAttribute(
                "assistantName",
                session.getAttribute("labAssistantName")
        );

        model.addAttribute(
                "assistantEmail",
                session.getAttribute("labAssistantEmail")
        );


        // Dashboard statistics
        model.addAttribute(
                "totalOrders",
                labOrderService.getAllOrders().size()
        );

        model.addAttribute(
                "totalTests",
                labTestService.getAllTests().size()
        );

        model.addAttribute(
                "totalSamples",
                sampleService.getAllSamples().size()
        );

        model.addAttribute(
                "totalReports",
                labReportService.getAllReports().size()
        );


        return "lab/dashboard";
    }
}
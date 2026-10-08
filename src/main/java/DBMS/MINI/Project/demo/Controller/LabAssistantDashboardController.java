package DBMS.MINI.Project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import DBMS.MINI.Project.demo.Service.LabAssistantDashboardService;

@Controller
public class LabAssistantDashboardController {

    @Autowired
    private LabAssistantDashboardService dashboardService;


    @GetMapping("/lab-assistant/dashboard")
    public String dashboard(Model model) {

        model.addAttribute(
            "totalOrders",
            dashboardService.getTotalOrders()
        );

        model.addAttribute(
            "pendingOrders",
            dashboardService.getPendingOrders()
        );

        model.addAttribute(
            "completedOrders",
            dashboardService.getCompletedOrders()
        );

        model.addAttribute(
            "cancelledOrders",
            dashboardService.getCancelledOrders()
        );

        model.addAttribute(
            "recentOrders",
            dashboardService.getRecentOrders()
        );

        return "lab-orders/dashboard";
    }
}
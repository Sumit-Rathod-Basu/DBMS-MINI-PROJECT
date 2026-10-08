package DBMS.MINI.Project.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

import DBMS.MINI.Project.demo.Service.labservice.LabOrderService;

@Controller
@RequestMapping("/lab/orders")
public class LabOrderController {

    @Autowired
    private LabOrderService orderService;


    @GetMapping
    public String orders(Model model) {

        model.addAttribute(
            "orders",
            orderService.getAllOrders()
        );

        return "lab-orders/list";
    }


    @GetMapping("/create")
    public String createPage(Model model) {

        model.addAttribute(
            "patients",
            orderService.getAllPatients()
        );

        model.addAttribute(
            "doctors",
            orderService.getAllDoctors()
        );

        model.addAttribute(
            "assistants",
            orderService.getAllAssistants()
        );

        model.addAttribute(
            "tests",
            orderService.getAllTests()
        );

        return "lab-orders/create";
    }


    @PostMapping("/create")
    public String createOrder(

            @RequestParam Long patientId,

            @RequestParam(required = false)
            Long doctorId,

            @RequestParam(required = false)
            Long assistantId,

            @RequestParam(required = false)
            Long[] testIds) {


        orderService.createOrder(
            patientId,
            doctorId,
            assistantId,
            testIds
        );


        return "redirect:/lab/orders";
    }


    @GetMapping("/{id}")
    public String viewOrder(
            @PathVariable Long id,
            Model model) {


        model.addAttribute(
            "order",
            orderService.getOrder(id)
        );


        model.addAttribute(
            "orderTests",
            orderService.getOrderTests(id)
        );


        return "lab-orders/view";
    }
}
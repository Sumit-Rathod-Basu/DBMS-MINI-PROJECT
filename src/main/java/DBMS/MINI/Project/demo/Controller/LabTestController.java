package DBMS.MINI.Project.demo.Controller;


import DBMS.MINI.Project.demo.Service.labservice.LabTestService;
import DBMS.MINI.Project.demo.entity.lab.LabTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lab/tests")
public class LabTestController {

    @Autowired
    private LabTestService labTestService;

    // Show all tests
    @GetMapping
    public String getAllTests(Model model) {

        model.addAttribute(
                "tests",
                labTestService.getAllTests()
        );

        return "lab/tests";
    }

    // Show add test form
    @GetMapping("/add")
    public String showAddTestForm(Model model) {

        model.addAttribute("test", new LabTest());

        return "lab/add-test";
    }

    // Save test
    @PostMapping("/save")
    public String saveTest(
            @ModelAttribute("test") LabTest test) {

        labTestService.saveTest(test);

        return "redirect:/lab/tests";
    }

    // Delete test
    @GetMapping("/delete/{id}")
    public String deleteTest(@PathVariable Long id) {

        labTestService.deleteTest(id);

        return "redirect:/lab/tests";
    }
}
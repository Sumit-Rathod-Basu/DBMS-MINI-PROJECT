package DBMS.MINI.Project.demo.Controller;
import DBMS.MINI.Project.demo.Service.labservice.LabAssistantService;
import DBMS.MINI.Project.demo.entity.lab.LabAssistant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lab/assistants")
public class LabAssistantController {

    @Autowired
    private LabAssistantService labAssistantService;

    // Show all assistants
    @GetMapping
    public String getAllAssistants(Model model) {

        model.addAttribute(
                "assistants",
                labAssistantService.getAllAssistants()
        );

        return "lab/assistants";
    }

    // Show add assistant form
    @GetMapping("/add")
    public String showAddAssistantForm(Model model) {

        model.addAttribute("assistant", new LabAssistant());

        return "lab/add-assistant";
    }

    // Save assistant
    @PostMapping("/save")
    public String saveAssistant(
            @ModelAttribute("assistant") LabAssistant assistant) {

        labAssistantService.saveAssistant(assistant);

        return "redirect:/lab/assistants";
    }

    // Delete assistant
    @GetMapping("/delete/{id}")
    public String deleteAssistant(@PathVariable Long id) {

        labAssistantService.deleteAssistant(id);

        return "redirect:/lab/assistants";
    }
}
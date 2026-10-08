package DBMS.MINI.Project.demo.Controller;

import DBMS.MINI.Project.demo.Service.labservice.LabOrderService;
import DBMS.MINI.Project.demo.Service.labservice.SampleService;
import DBMS.MINI.Project.demo.entity.lab.Sample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lab/samples")
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private LabOrderService labOrderService;

    // Show all samples
    @GetMapping
    public String getAllSamples(Model model) {

        model.addAttribute(
                "samples",
                sampleService.getAllSamples()
        );

        return "lab/samples";
    }

    // Add sample page
    @GetMapping("/add")
    public String showAddSampleForm(Model model) {

        model.addAttribute("sample", new Sample());

        model.addAttribute(
                "orders",
                labOrderService.getAllOrders()
        );

        return "lab/add-sample";
    }

    // Save sample
    @PostMapping("/save")
    public String saveSample(
            @ModelAttribute("sample") Sample sample) {

        sampleService.saveSample(sample);

        return "redirect:/lab/samples";
    }

    // Delete sample
    @GetMapping("/delete/{id}")
    public String deleteSample(@PathVariable Long id) {

        sampleService.deleteSample(id);

        return "redirect:/lab/samples";
    }
}
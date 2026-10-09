package DBMS.MINI.Project.demo.Controller;

import DBMS.MINI.Project.demo.Service.labservice.SampleService;
import DBMS.MINI.Project.demo.entity.lab.Sample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lab/samples")
public class SampleController {

    private final SampleService sampleService;

    // =========================
    // SAMPLE LIST
    // =========================

    @GetMapping
    public String samples(Model model) {

        model.addAttribute(
                "samples",
                sampleService.getAllSamples()
        );

        return "lab/samples";
    }

    // =========================
    // NEW SAMPLE FORM
    // =========================

    @GetMapping("/new")
    public String newSample(Model model) {

        model.addAttribute(
                "sample",
                new Sample()
        );

        return "lab/add-sample";
    }

    // =========================
    // SAVE SAMPLE
    // =========================

    @PostMapping("/save")
    public String saveSample(
            @ModelAttribute("sample") Sample sample) {

        sampleService.createSample(sample);

        return "redirect:/lab/samples";
    }

    // =========================
    // SAMPLE DETAILS
    // =========================

    @GetMapping("/{id}")
    public String sampleDetails(
            @PathVariable Long id,
            Model model) {

        Sample sample =
                sampleService.getSampleById(id);

        model.addAttribute("sample", sample);

        return "lab/sample-details";
    }

    // =========================
    // SCAN BARCODE PAGE
    // =========================

    @GetMapping("/scan")
    public String scanPage() {

        return "lab/sample-scan";
    }

    // =========================
    // SEARCH BARCODE
    // =========================

    @GetMapping("/scan/search")
    public String searchBarcode(
            @RequestParam String barcode,
            Model model) {

        try {

            Sample sample =
                    sampleService.getByBarcode(barcode);

            model.addAttribute("sample", sample);

            return "lab/sample-details";

        } catch (RuntimeException e) {

            model.addAttribute(
                    "error",
                    "No sample found for barcode: " + barcode
            );

            return "lab/sample-scan";
        }
    }

    // =========================
    // UPDATE STATUS
    // =========================

    @PostMapping("/{id}/status")
    public String updateStatus(
            @PathVariable Long id,
            @RequestParam Sample.SampleStatus status) {

        sampleService.updateStatus(id, status);

        return "redirect:/lab/samples/" + id;
    }

    // =========================
    // DELETE
    // =========================

    @PostMapping("/{id}/delete")
    public String deleteSample(
            @PathVariable Long id) {

        sampleService.deleteSample(id);

        return "redirect:/lab/samples";
    }
}
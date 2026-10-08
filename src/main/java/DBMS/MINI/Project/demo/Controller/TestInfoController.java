package DBMS.MINI.Project.demo.Controller;

import DBMS.MINI.Project.demo.Service.TestInfoService;
import DBMS.MINI.Project.demo.entity.lab.PatientInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/lab/patient")
@RequiredArgsConstructor
public class TestInfoController {

    private final TestInfoService testInfoService;

    // ==========================================
    // STEP 2 - SHOW TEST PAGE
    // ==========================================

    @GetMapping("/{patientId}/tests")
    public String showTestPage(
            @PathVariable Long patientId,
            Model model) {

        PatientInfo patient =
                testInfoService.getPatient(patientId);

        model.addAttribute("patientInfo", patient);

        model.addAttribute(
                "tests",
                testInfoService.getPatientTests(patientId)
        );

        return "patients/test-info";
    }

    // ==========================================
    // SAVE MULTIPLE TESTS
    // ==========================================

    @PostMapping("/{patientId}/tests/save")
    public String saveTests(
            @PathVariable Long patientId,

            @RequestParam("categoryName")
            List<String> categoryNames,

            @RequestParam("testName")
            List<String> testNames,

            @RequestParam("resultValue")
            List<String> resultValues,

            @RequestParam("unit")
            List<String> units,

            @RequestParam("referenceRange")
            List<String> referenceRanges,

            @RequestParam("status")
            List<String> statuses,

            @RequestParam("remarks")
            List<String> remarks) {

        testInfoService.saveTests(
                patientId,
                categoryNames,
                testNames,
                resultValues,
                units,
                referenceRanges,
                statuses,
                remarks
        );

return "redirect:/lab/report/" + patientId;
    }
}
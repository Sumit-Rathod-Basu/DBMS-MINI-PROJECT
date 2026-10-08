package DBMS.MINI.Project.demo.Controller;

import DBMS.MINI.Project.demo.Service.PatientInfoService;
import DBMS.MINI.Project.demo.Service.TestInfoService;
import DBMS.MINI.Project.demo.entity.lab.PatientInfo;
import DBMS.MINI.Project.demo.entity.lab.TestInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Period;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class LabReportController {

    private final PatientInfoService patientInfoService;
    private final TestInfoService testInfoService;

    // =====================================================
    // FINAL LAB REPORT
    // =====================================================

    @GetMapping("/lab/report/{patientId}")
    public String viewReport(
            @PathVariable Long patientId,
            Model model) {

        PatientInfo patientInfo =
                patientInfoService.getPatientById(patientId);

        List<TestInfo> tests =
                testInfoService.getPatientTests(patientId);

        // Group tests by category
        Map<String, List<TestInfo>> groupedTests =
                tests.stream()
                        .collect(Collectors.groupingBy(
                                TestInfo::getCategoryName,
                                LinkedHashMap::new,
                                Collectors.toList()
                        ));

        // Calculate age
        int age = 0;

        if (patientInfo.getDateOfBirth() != null) {

            age = Period.between(
                    patientInfo.getDateOfBirth(),
                    LocalDate.now()
            ).getYears();
        }

        model.addAttribute("patientInfo", patientInfo);
        model.addAttribute("tests", tests);
        model.addAttribute("groupedTests", groupedTests);
        model.addAttribute("age", age);

        return "lab/report/final-report";
    }

    // =====================================================
    // VERIFY REPORT BY BILL NUMBER
    // =====================================================

    @GetMapping("/verify/{billNumber}")
    public String verifyReport(
            @PathVariable String billNumber,
            Model model) {

        System.out.println(
                "VERIFY REQUEST RECEIVED: " + billNumber
        );

        try {

            PatientInfo patientInfo =
                    patientInfoService
                            .getPatientByBillNumber(billNumber);

            List<TestInfo> tests =
                    testInfoService.getPatientTests(
                            patientInfo.getId()
                    );

            model.addAttribute(
                    "patientInfo",
                    patientInfo
            );

            model.addAttribute(
                    "tests",
                    tests
            );

            return "lab/report/verify";

        } catch (Exception e) {

            e.printStackTrace();

            model.addAttribute(
                    "billNumber",
                    billNumber
            );

            model.addAttribute(
                    "error",
                    "No report found for bill number: "
                            + billNumber
            );

            return "lab/report/verification-error";
        }
    }
}
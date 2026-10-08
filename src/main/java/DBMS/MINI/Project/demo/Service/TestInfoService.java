package DBMS.MINI.Project.demo.Service;


import DBMS.MINI.Project.demo.entity.lab.PatientInfo;
import DBMS.MINI.Project.demo.entity.lab.TestInfo;
import DBMS.MINI.Project.demo.repository.labrepo.PatientInfoRepository;
import DBMS.MINI.Project.demo.repository.labrepo.TestInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestInfoService {

    private final TestInfoRepository testInfoRepository;
    private final PatientInfoRepository patientInfoRepository;

    // ==========================================
    // GET PATIENT
    // ==========================================

    public PatientInfo getPatient(Long patientId) {

        return patientInfoRepository.findById(patientId)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient not found with ID: " + patientId
                        ));
    }

    // ==========================================
    // GET ALL TESTS OF PATIENT
    // ==========================================

    public List<TestInfo> getPatientTests(Long patientId) {

        return testInfoRepository.findByPatientInfoId(patientId);
    }

    // ==========================================
    // SAVE MULTIPLE TESTS
    // ==========================================

    @Transactional
    public void saveTests(
            Long patientId,
            List<String> categoryNames,
            List<String> testNames,
            List<String> resultValues,
            List<String> units,
            List<String> referenceRanges,
            List<String> statuses,
            List<String> remarks) {

        PatientInfo patient =
                getPatient(patientId);

        for (int i = 0; i < testNames.size(); i++) {

            // Skip completely empty rows
            if (testNames.get(i) == null ||
                    testNames.get(i).isBlank()) {
                continue;
            }

            TestInfo test = new TestInfo();

            test.setPatientInfo(patient);

            test.setCategoryName(
                    getValue(categoryNames, i)
            );

            test.setTestName(
                    getValue(testNames, i)
            );

            test.setResultValue(
                    getValue(resultValues, i)
            );

            test.setUnit(
                    getValue(units, i)
            );

            test.setReferenceRange(
                    getValue(referenceRanges, i)
            );

            test.setStatus(
                    getValue(statuses, i)
            );

            test.setRemarks(
                    getValue(remarks, i)
            );

            testInfoRepository.save(test);
        }
    }

    // ==========================================
    // UPDATE / SAVE SINGLE TEST
    // ==========================================

    public TestInfo saveTest(TestInfo test) {

        return testInfoRepository.save(test);
    }

    // ==========================================
    // DELETE TEST
    // ==========================================

    public void deleteTest(Long testId) {

        testInfoRepository.deleteById(testId);
    }

    // ==========================================
    // SAFE LIST VALUE
    // ==========================================

    private String getValue(List<String> list, int index) {

        if (list == null || index >= list.size()) {
            return "";
        }

        return list.get(index) == null
                ? ""
                : list.get(index).trim();
    }
}
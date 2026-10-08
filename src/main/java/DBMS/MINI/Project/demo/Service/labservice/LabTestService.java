package DBMS.MINI.Project.demo.Service.labservice;

import DBMS.MINI.Project.demo.entity.lab.LabTest;
import DBMS.MINI.Project.demo.repository.labrepo.LabTestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabTestService {

    @Autowired
    private LabTestRepository labTestRepository;

    // Get all tests
    public List<LabTest> getAllTests() {
        return labTestRepository.findAll();
    }

    // Get test by ID
    public LabTest getTestById(Long id) {
        return labTestRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Lab test not found"));
    }

    // Save test
    public LabTest saveTest(LabTest test) {
        return labTestRepository.save(test);
    }

    // Delete test
    public void deleteTest(Long id) {
        labTestRepository.deleteById(id);
    }
}
package DBMS.MINI.Project.demo.Service.labservice;

import DBMS.MINI.Project.demo.entity.lab.Sample;
import DBMS.MINI.Project.demo.repository.labrepo.SampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SampleService {

    @Autowired
    private SampleRepository sampleRepository;

    // Get all samples
    public List<Sample> getAllSamples() {
        return sampleRepository.findAll();
    }

    // Get sample by ID
    public Sample getSampleById(Long id) {
        return sampleRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Sample not found"));
    }

    // Save sample
    public Sample saveSample(Sample sample) {
        return sampleRepository.save(sample);
    }

    // Delete sample
    public void deleteSample(Long id) {
        sampleRepository.deleteById(id);
    }
}
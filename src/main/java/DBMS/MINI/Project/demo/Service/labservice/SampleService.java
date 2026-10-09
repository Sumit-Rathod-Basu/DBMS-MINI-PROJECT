package DBMS.MINI.Project.demo.Service.labservice;

import DBMS.MINI.Project.demo.entity.lab.Sample;
import DBMS.MINI.Project.demo.repository.labrepo.SampleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SampleService {

    private final SampleRepository sampleRepository;

    // Get all samples
    public List<Sample> getAllSamples() {
        return sampleRepository.findAll();
    }

    // Get sample by ID
    public Sample getSampleById(Long id) {
        return sampleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sample not found"));
    }

    // Get sample by sample code
    public Sample getBySampleCode(String sampleCode) {
        return sampleRepository.findBySampleCode(sampleCode)
                .orElseThrow(() -> new RuntimeException("Sample not found"));
    }

    // Get sample by barcode
    public Sample getByBarcode(String barcode) {
        return sampleRepository.findByBarcodeValue(barcode)
                .orElseThrow(() -> new RuntimeException("Sample not found"));
    }

    // Search by patient name
    public List<Sample> searchByPatientName(String patientName) {
        return sampleRepository
                .findByPatientNameContainingIgnoreCase(patientName);
    }

    // Create sample
    public Sample createSample(Sample sample) {

        // Generate sample code automatically
        if (sample.getSampleCode() == null ||
                sample.getSampleCode().isBlank()) {

            sample.setSampleCode(
                    "SMP-" +
                    System.currentTimeMillis()
            );
        }

        // Generate barcode automatically
        if (sample.getBarcodeValue() == null ||
                sample.getBarcodeValue().isBlank()) {

            sample.setBarcodeValue(sample.getSampleCode());
        }

        // Date automatically
        if (sample.getCollectionDate() == null) {
            sample.setCollectionDate(LocalDate.now());
        }

        // Time automatically
        if (sample.getCollectionTime() == null) {
            sample.setCollectionTime(LocalTime.now());
        }

        // Default status
        if (sample.getStatus() == null) {
            sample.setStatus(Sample.SampleStatus.COLLECTED);
        }

        return sampleRepository.save(sample);
    }

    // Update sample
    public Sample updateSample(Long id, Sample updatedSample) {

        Sample existingSample = getSampleById(id);

        existingSample.setPatientName(updatedSample.getPatientName());
        existingSample.setAge(updatedSample.getAge());
        existingSample.setTestName(updatedSample.getTestName());
        existingSample.setSampleType(updatedSample.getSampleType());
        existingSample.setCollectionDate(updatedSample.getCollectionDate());
        existingSample.setCollectionTime(updatedSample.getCollectionTime());
        existingSample.setCollectedBy(updatedSample.getCollectedBy());
        existingSample.setStatus(updatedSample.getStatus());

        return sampleRepository.save(existingSample);
    }

    // Update status
    public Sample updateStatus(Long id, Sample.SampleStatus status) {

        Sample sample = getSampleById(id);

        sample.setStatus(status);

        return sampleRepository.save(sample);
    }

    // Delete sample
    public void deleteSample(Long id) {
        sampleRepository.deleteById(id);
    }
}
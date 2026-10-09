package DBMS.MINI.Project.demo.repository.labrepo;

import DBMS.MINI.Project.demo.entity.lab.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SampleRepository extends JpaRepository<Sample, Long> {

    Optional<Sample> findBySampleCode(String sampleCode);

    Optional<Sample> findByBarcodeValue(String barcodeValue);

    List<Sample> findByPatientNameContainingIgnoreCase(String patientName);

    List<Sample> findByStatus(Sample.SampleStatus status);
}
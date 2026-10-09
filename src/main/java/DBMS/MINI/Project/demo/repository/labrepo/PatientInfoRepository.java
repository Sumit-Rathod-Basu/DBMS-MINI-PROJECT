package DBMS.MINI.Project.demo.repository.labrepo;

import DBMS.MINI.Project.demo.entity.lab.PatientInfo;
import DBMS.MINI.Project.demo.entity.patientlogin.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PatientInfoRepository
        extends JpaRepository<PatientInfo, Long> {

    Optional<PatientInfo> findByBillNumber(String billNumber);

    Optional<PatientInfo> findByEmail(String email);

    List<PatientInfo> findByPatientNameContainingIgnoreCase(String patientName);

    Optional<PatientInfo> findByMobile(String mobile);

    boolean existsByBillNumber(String billNumber);

    List findAll();

    Optional<patient> findByEmail(Long patientId);

    
}
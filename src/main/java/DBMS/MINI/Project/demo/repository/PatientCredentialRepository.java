package DBMS.MINI.Project.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import DBMS.MINI.Project.demo.entity.patientlogin.PatientCredential;

import java.util.Optional;

public interface PatientCredentialRepository
        extends JpaRepository<PatientCredential, Long> {

    Optional<PatientCredential> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByPatientPatientId(Long patientId);
}
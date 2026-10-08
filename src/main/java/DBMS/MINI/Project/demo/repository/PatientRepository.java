package DBMS.MINI.Project.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import DBMS.MINI.Project.demo.entity.patientlogin.patient;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<patient, Long> {

    Optional<patient> findByMobile(String mobile);

    List<patient    > findByFirstNameContainingIgnoreCase(String firstName);

    List<patient> findByLastNameContainingIgnoreCase(String lastName);

    List<patient> findByStatus(String status);

    boolean existsByMobile(String mobile);

    Optional<patient> findByEmail(String email);
}
package DBMS.MINI.Project.demo.repository.labrepo;

import DBMS.MINI.Project.demo.entity.lab.LabReport;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository
        extends JpaRepository<LabReport, Long> {

}
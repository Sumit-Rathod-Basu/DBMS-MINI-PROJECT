package DBMS.MINI.Project.demo.repository.labrepo;

import DBMS.MINI.Project.demo.entity.lab.Sample;

import org.springframework.data.jpa.repository.JpaRepository;


public interface SampleRepository
        extends JpaRepository<Sample, Long> {
}
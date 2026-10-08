package DBMS.MINI.Project.demo.repository.labrepo;

import DBMS.MINI.Project.demo.entity.lab.LabOrder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LabOrderRepository
        extends JpaRepository<LabOrder, Long> {

    List<LabOrder>
    findTop10ByOrderByOrderDateDesc();

    long countByStatus(String status);
}
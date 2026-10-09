package DBMS.MINI.Project.demo.repository.labrepo;
import DBMS.MINI.Project.demo.entity.lab.LabOrder;
import DBMS.MINI.Project.demo.entity.lab.TestInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestInfoRepository extends JpaRepository<TestInfo, Long> {

    List<TestInfo> findByPatientInfoId(Long patientId);

    void deleteByPatientInfoId(Long patientId);

    
}
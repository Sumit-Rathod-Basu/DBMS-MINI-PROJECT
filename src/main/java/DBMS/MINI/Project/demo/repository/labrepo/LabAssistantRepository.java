package DBMS.MINI.Project.demo.repository.labrepo;

import DBMS.MINI.Project.demo.entity.lab.LabAssistant;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LabAssistantRepository extends JpaRepository<LabAssistant, Long> {

    Optional<LabAssistant> findByEmail(String email);
}
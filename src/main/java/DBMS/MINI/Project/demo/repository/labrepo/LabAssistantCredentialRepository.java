package DBMS.MINI.Project.demo.repository.labrepo;


import DBMS.MINI.Project.demo.entity.lab.LabAssistant;
import DBMS.MINI.Project.demo.entity.lab.LabAssistantCredential;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface LabAssistantCredentialRepository
        extends JpaRepository<LabAssistantCredential, Long> {

    Optional<LabAssistantCredential> findByAssistant(LabAssistant assistant);
}
package DBMS.MINI.Project.demo.Service.labservice;

import DBMS.MINI.Project.demo.entity.lab.LabAssistant;
import DBMS.MINI.Project.demo.repository.labrepo.LabAssistantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabAssistantService {

    @Autowired
    private LabAssistantRepository labAssistantRepository;

    // Get all lab assistants
    public List<LabAssistant> getAllAssistants() {
        return labAssistantRepository.findAll();
    }

    // Get assistant by ID
    public LabAssistant getAssistantById(Long id) {
        return labAssistantRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Lab Assistant not found"));
    }

    // Save assistant
    public LabAssistant saveAssistant(LabAssistant assistant) {
        return labAssistantRepository.save(assistant);
    }

    // Delete assistant
    public void deleteAssistant(Long id) {
        labAssistantRepository.deleteById(id);
    }
}
package DBMS.MINI.Project.demo.Service.labservice;

import DBMS.MINI.Project.demo.entity.lab.LabAssistant;
import DBMS.MINI.Project.demo.entity.lab.LabAssistantCredential;
import DBMS.MINI.Project.demo.repository.labrepo.LabAssistantCredentialRepository;
import DBMS.MINI.Project.demo.repository.labrepo.LabAssistantRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LabLoginService {

    @Autowired
    private LabAssistantRepository labAssistantRepository;

    @Autowired
    private LabAssistantCredentialRepository credentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    // =========================
    // REGISTER
    // =========================
    public LabAssistant register(
            String fullName,
            String email,
            String mobile,
            String password) {

        // Check email
        if (labAssistantRepository.findByEmail(email).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        // Create assistant
        LabAssistant assistant = new LabAssistant();

        assistant.setFullName(fullName);
        assistant.setEmail(email);
        assistant.setMobile(mobile);
        assistant.setStatus("ACTIVE");

        // Save assistant
        LabAssistant savedAssistant =
                labAssistantRepository.save(assistant);


        // Create credential
        LabAssistantCredential credential =
                new LabAssistantCredential();

        credential.setAssistant(savedAssistant);

        // IMPORTANT:
        // Store encrypted password
        credential.setPassword(
                passwordEncoder.encode(password)
        );

        credential.setStatus("ACTIVE");

        // Save credential
        credentialRepository.save(credential);

        return savedAssistant;
    }


    // =========================
    // LOGIN
    // =========================
    public LabAssistant login(
            String email,
            String password) {

        // Find assistant by email
        LabAssistant assistant =
                labAssistantRepository
                        .findByEmail(email)
                        .orElse(null);

        if (assistant == null) {
            return null;
        }


        // Check assistant status
        if (!"ACTIVE".equalsIgnoreCase(
                assistant.getStatus())) {

            return null;
        }


        // Find password record
        LabAssistantCredential credential =
                credentialRepository
                        .findByAssistant(assistant)
                        .orElse(null);

        if (credential == null) {
            return null;
        }


        // Check credential status
        if (!"ACTIVE".equalsIgnoreCase(
                credential.getStatus())) {

            return null;
        }


        // Compare entered password
        // with BCrypt password
        boolean passwordMatches =
                passwordEncoder.matches(
                        password,
                        credential.getPassword()
                );


        if (!passwordMatches) {
            return null;
        }


        // Login successful
        return assistant;
    }
}
package DBMS.MINI.Project.demo.Service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import DBMS.MINI.Project.demo.repository.PatientCredentialRepository;
import DBMS.MINI.Project.demo.repository.PatientRepository;
import DBMS.MINI.Project.demo.entity.patientlogin.PatientCredential;
import DBMS.MINI.Project.demo.entity.patientlogin.patient;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PatientAuthenticationService {

    private final PatientRepository patientRepository;

    private final PatientCredentialRepository credentialRepository;

    private final PasswordEncoder passwordEncoder;


    // ==========================
    // PATIENT SIGNUP
    // ==========================

    public void signup(
            String email,
            String password,
            String confirmPassword
    ) {

        email = email.trim().toLowerCase();


        // 1. Check whether email exists
        patient patient =
                patientRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "This email is not registered with our laboratory."
                                )
                        );


        // 2. Check whether account already exists
        if (credentialRepository
                .existsByPatientPatientId(
                        patient.getPatientId()
                )) {

            throw new RuntimeException(
                    "Account already exists. Please login."
            );
        }


        // 3. Check password
        if (!password.equals(confirmPassword)) {

            throw new RuntimeException(
                    "Passwords do not match."
            );
        }


        // 4. Hash password
        String passwordHash =
                passwordEncoder.encode(password);


        // 5. Create credentials
        PatientCredential credential = new PatientCredential();
        credential.setPatient(patient);
        credential.setEmail(email);
        credential.setPasswordHash(passwordHash);
        credential.setStatus("ACTIVE");


        // 6. Save separately
        credentialRepository.save(credential);
    }


    // ==========================
    // PATIENT LOGIN
    // ==========================

    public patient login(
            String email,
            String password
    ) {

        email = email.trim().toLowerCase();


        // Find credentials
        PatientCredential credential =
                credentialRepository
                        .findByEmail(email)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invalid email or password."
                                )
                        );


        // Check password
        boolean valid =
                passwordEncoder.matches(
                        password,
                        credential.getPasswordHash()
                );


        if (!valid) {

            throw new RuntimeException(
                    "Invalid email or password."
            );
        }


        // Update last login
        credential.setLastLogin(
                LocalDateTime.now()
        );

        credentialRepository.save(credential);


        // Return patient
        return credential.getPatient();
    }
}
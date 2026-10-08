package DBMS.MINI.Project.demo.Controller;

import DBMS.MINI.Project.demo.Service.PatientService;
import DBMS.MINI.Project.demo.entity.patientlogin.patient;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    @PostMapping
    public ResponseEntity<patient> createPatient(
            @Valid @RequestBody patient patient) {

        patient savedPatient = patientService.createPatient(patient);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedPatient);
    }

    @GetMapping
    public ResponseEntity<List<patient>> getAllPatients() {

        return ResponseEntity.ok(
                patientService.getAllPatients()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<patient> getPatientById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                patientService.getPatientById(id)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<patient> updatePatient(
            @PathVariable Long id,
            @Valid @RequestBody patient patient) {

        return ResponseEntity.ok(
                patientService.updatePatient(id, patient)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(
            @PathVariable Long id) {

        patientService.deletePatient(id);

        return ResponseEntity.ok(
                "Patient marked as inactive successfully"
        );
    }

    @GetMapping("/search")
    public ResponseEntity<List<patient>> searchPatients(
            @RequestParam String name) {

        return ResponseEntity.ok(
                patientService.searchByFirstName(name)
        );
    }
}
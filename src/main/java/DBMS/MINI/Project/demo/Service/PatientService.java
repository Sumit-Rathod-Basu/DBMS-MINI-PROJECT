package DBMS.MINI.Project.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import DBMS.MINI.Project.demo.entity.patientlogin.patient;
import DBMS.MINI.Project.demo.repository.PatientRepository;
import java.util.List;

@Service
public class PatientService {   
    
    @Autowired 
    private  PatientRepository patientRepository;   

    public patient createPatient(patient patient) {

        if (patientRepository.existsByMobile(patient.getMobile())) {
            throw new RuntimeException(
                "Patient with this mobile number already exists"
            );
        }

        return patientRepository.save(patient);
    }

    public List<patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Patient not found with ID: " + id));
    }

    public patient updatePatient(Long id, patient updatedPatient) {

        patient existingPatient = getPatientById(id);

        existingPatient.setFirstName(updatedPatient.getFirstName());
        existingPatient.setLastName(updatedPatient.getLastName());
        existingPatient.setGender(updatedPatient.getGender());
        existingPatient.setDateOfBirth(updatedPatient.getDateOfBirth());
        existingPatient.setMobile(updatedPatient.getMobile());
        existingPatient.setEmail(updatedPatient.getEmail());
        existingPatient.setAddress(updatedPatient.getAddress());
        existingPatient.setBloodGroup(updatedPatient.getBloodGroup());
        existingPatient.setEmergencyContactName(
                updatedPatient.getEmergencyContactName()
        );
        existingPatient.setEmergencyContactNumber(
                updatedPatient.getEmergencyContactNumber()
        );

        return patientRepository.save(existingPatient);
    }

    public void deletePatient(Long id) {

        patient patient = getPatientById(id);

        patient.setStatus("INACTIVE");

        patientRepository.save(patient);
    }

    public List<patient> searchByFirstName(String name) {
        return patientRepository
                .findByFirstNameContainingIgnoreCase(name);
    }
}
package DBMS.MINI.Project.demo.Service;


import DBMS.MINI.Project.demo.entity.lab.PatientInfo;
import DBMS.MINI.Project.demo.repository.labrepo.PatientInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientInfoService {

    private final PatientInfoRepository patientInfoRepository;

    // ==========================================
    // CREATE PATIENT BILL
    // ==========================================

    @Transactional
    public PatientInfo createPatient(PatientInfo patientInfo) {

        // Generate bill number
        String billNumber = generateBillNumber();

        patientInfo.setBillNumber(billNumber);

        // Automatically set date and time
        patientInfo.setBillDate(LocalDate.now());
        patientInfo.setBillTime(LocalTime.now());

        // Default values
        if (patientInfo.getSubtotal() == null) {
            patientInfo.setSubtotal(BigDecimal.ZERO);
        }

        if (patientInfo.getDiscount() == null) {
            patientInfo.setDiscount(BigDecimal.ZERO);
        }

        if (patientInfo.getTotalAmount() == null) {
            patientInfo.setTotalAmount(BigDecimal.ZERO);
        }

        if (patientInfo.getPaymentStatus() == null ||
                patientInfo.getPaymentStatus().isBlank()) {

            patientInfo.setPaymentStatus("PENDING");
        }

        return patientInfoRepository.save(patientInfo);
    }

    // ==========================================
    // FIND PATIENT BY ID
    // ==========================================

    public PatientInfo getPatientById(Long id) {

        return patientInfoRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Patient not found with ID: " + id
                        ));
    }

    // ==========================================
// GET ALL REGISTERED PATIENTS
// ==========================================

public List<PatientInfo> getAllPatients() {

    return patientInfoRepository.findAll(
    );
}

    // ==========================================
    // GENERATE BILL NUMBER
    // ==========================================
// ==========================================
// FIND PATIENT BY BILL NUMBER
// ==========================================

    private String generateBillNumber() {

        String date =
                LocalDate.now()
                        .toString()
                        .replace("-", "");

        long count =
                patientInfoRepository.count() + 1;

        return String.format(
                "RR-%s-%04d",
                date,
                count
        );
    }
    public PatientInfo getPatientByBillNumber(String billNumber) {

    return patientInfoRepository
            .findByBillNumber(billNumber)
            .orElseThrow(() ->
                    new RuntimeException(
                            "Patient not found for bill: "
                                    + billNumber
                    ));
}
}
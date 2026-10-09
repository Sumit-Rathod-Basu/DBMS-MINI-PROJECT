package DBMS.MINI.Project.demo.entity.lab;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "patient_info")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PatientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // PATIENT INFORMATION
    // =========================

    @Column(nullable = false, length = 100)
    private String patientName;

    @Column(nullable = false, length = 10)
    private String mobile;

    @Column(length = 150)
    private String email;

    @Column(length = 20)
    private String gender;

    private LocalDate dateOfBirth;

    @Column(length = 255)
    private String address;

    // =========================
    // BILL INFORMATION
    // =========================

    @Column(nullable = false, unique = true, length = 50)
    private String billNumber;

    @Column(nullable = false)
    private LocalDate billDate;

    @Column(nullable = false)
    private LocalTime billTime;

    // =========================
    // BILL AMOUNT
    // =========================

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal subtotal = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Column(length = 20)
    private String paymentStatus;

    // =========================
    // CREATED TIME
    // =========================

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {

        if (billDate == null) {
            billDate = LocalDate.now();
        }

        if (billTime == null) {
            billTime = LocalTime.now();
        }

        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (subtotal == null) {
            subtotal = BigDecimal.ZERO;
        }

        if (discount == null) {
            discount = BigDecimal.ZERO;
        }

        if (totalAmount == null) {
            totalAmount = BigDecimal.ZERO;
        }

        if (paymentStatus == null) {
            paymentStatus = "PENDING";
        }
    }
}
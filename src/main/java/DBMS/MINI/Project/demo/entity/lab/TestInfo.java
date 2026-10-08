package DBMS.MINI.Project.demo.entity.lab;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "test")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ==========================================
    // PATIENT
    // ==========================================

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientInfo patientInfo;

    // ==========================================
    // TEST INFORMATION
    // ==========================================

    @Column(nullable = false, length = 100)
    private String categoryName;

    @Column(nullable = false, length = 150)
    private String testName;

    @Column(length = 100)
    private String resultValue;

    @Column(length = 50)
    private String unit;

    @Column(length = 100)
    private String referenceRange;

    @Column(length = 20)
    private String status;

    @Column(length = 500)
    private String remarks;
}
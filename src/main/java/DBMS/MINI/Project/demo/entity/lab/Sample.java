package DBMS.MINI.Project.demo.entity.lab;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "samples")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Sample {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Unique sample number
    @Column(name = "sample_code", unique = true, nullable = false)
    private String sampleCode;

    // Patient name - stored for easy display
    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @Column(name = "age", nullable = false)
    private String age;

    // Test name
    @Column(nullable = false)
    private String testName;

    // Blood / Urine / Stool etc.
    @Column(nullable = false)
    private String sampleType;

    // Date and time of collection
    @Column(nullable = false)
    private LocalDate collectionDate;

    @Column(nullable = false)
    private LocalTime collectionTime;

    // Person who collected sample
    private String collectedBy;

    // Barcode value
    @Column(name = "barcode_value", unique = true, nullable = false)
    private String barcodeValue;

    // Sample status
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SampleStatus status;

    public enum SampleStatus {
        COLLECTED,
        RECEIVED,
        PROCESSING,
        COMPLETED,
        REJECTED,
        CANCELLED
    }
}
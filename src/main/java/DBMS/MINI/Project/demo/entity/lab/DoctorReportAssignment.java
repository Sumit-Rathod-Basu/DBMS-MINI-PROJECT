package DBMS.MINI.Project.demo.entity.lab;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "doctor_report_assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorReportAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /*
     * Doctor who is assigned to review the report.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    /*
     * Existing TestInfo/report.
     *
     * IMPORTANT:
     * Replace TestInfo package below if your existing
     * TestInfo class is in another package.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "test_info_id", nullable = false)
    private TestInfo testInfo;

    /*
     * Assignment status.
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReviewStatus status;

    /*
     * Doctor's medical remark.
     */
    @Column(name = "doctor_remark", columnDefinition = "TEXT")
    private String doctorRemark;

    /*
     * Doctor's suggestion.
     */
    @Column(name = "doctor_suggestion", columnDefinition = "TEXT")
    private String doctorSuggestion;

    /*
     * Who assigned the report.
     * Example: Lab Assistant / Admin
     */
    @Column(name = "assigned_by")
    private String assignedBy;

    @Column(name = "assigned_at")
    private LocalDateTime assignedAt;

    /*
     * When doctor started reviewing.
     */
    @Column(name = "review_started_at")
    private LocalDateTime reviewStartedAt;

    /*
     * Doctor who verified it.
     */
    @Column(name = "verified_by")
    private String verifiedBy;

    @Column(name = "verified_at")
    private LocalDateTime verifiedAt;

    public enum ReviewStatus {

        ASSIGNED_TO_DOCTOR,

        UNDER_REVIEW,

        NEEDS_CORRECTION,

        VERIFIED
    }
}
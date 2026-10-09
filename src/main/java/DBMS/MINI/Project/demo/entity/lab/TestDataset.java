package DBMS.MINI.Project.demo.entity.lab;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "test_dataset")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TestDataset {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@Column(name = "test_code", nullable = false, unique = true, length = 30)
private String testCode;

@Column(name = "test_name", nullable = false, length = 150)
private String testName;

@Column(name = "category_name", nullable = false, length = 100)
private String categoryName;

@Column(name = "specimen_type", nullable = false, length = 60)
private String specimenType;

@Column(name = "unit", nullable = false, length = 50)
private String unit;

@Column(name = "normal_min", nullable = false, precision = 12, scale = 3)
private BigDecimal normalMin;

@Column(name = "normal_max", nullable = false, precision = 12, scale = 3)
private BigDecimal normalMax;

@Column(name = "critical_low", precision = 12, scale = 3)
private BigDecimal criticalLow;

@Column(name = "critical_high", precision = 12, scale = 3)
private BigDecimal criticalHigh;

@Column(name = "active", nullable = false)
@Builder.Default
private Boolean active = true;

}

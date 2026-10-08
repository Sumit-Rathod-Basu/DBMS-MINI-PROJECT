package DBMS.MINI.Project.demo.entity.lab;

import jakarta.persistence.*;

@Entity
@Table(
    name = "order_tests",
    uniqueConstraints = {
        @UniqueConstraint(
            columnNames = {"order_id", "test_id"}
        )
    }
)
public class OrderTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderTestId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private LabOrder order;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "test_id", nullable = false)
    private LabTest test;


    private String resultValue;

    private String resultStatus;

    private String remarks;


    public OrderTest() {
    }


    public Long getOrderTestId() {
        return orderTestId;
    }

    public void setOrderTestId(Long orderTestId) {
        this.orderTestId = orderTestId;
    }


    public LabOrder getOrder() {
        return order;
    }

    public void setOrder(LabOrder order) {
        this.order = order;
    }


    public LabTest getTest() {
        return test;
    }

    public void setTest(LabTest test) {
        this.test = test;
    }


    public String getResultValue() {
        return resultValue;
    }

    public void setResultValue(String resultValue) {
        this.resultValue = resultValue;
    }


    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }


    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
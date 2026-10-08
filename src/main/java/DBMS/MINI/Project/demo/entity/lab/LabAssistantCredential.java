package DBMS.MINI.Project.demo.entity.lab;

import jakarta.persistence.*;

@Entity
@Table(name = "lab_assistant_credentials")
public class LabAssistantCredential {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long credentialId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "assistant_id",
            nullable = false,
            unique = true
    )
    private LabAssistant assistant;

    @Column(nullable = false)
    private String password;

    private String status;

    public LabAssistantCredential() {
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public LabAssistant getAssistant() {
        return assistant;
    }

    public void setAssistant(LabAssistant assistant) {
        this.assistant = assistant;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
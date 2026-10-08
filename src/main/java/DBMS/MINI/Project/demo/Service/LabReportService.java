package DBMS.MINI.Project.demo.Service;

import DBMS.MINI.Project.demo.entity.lab.LabReport;
import DBMS.MINI.Project.demo.repository.labrepo.ReportRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabReportService {

    @Autowired
    private ReportRepository labReportRepository;

    // Get all reports
    public List<LabReport> getAllReports() {
        return labReportRepository.findAll();
    }

    // Get report by ID
    public LabReport getReportById(Long id) {
        return labReportRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Lab report not found"));
    }

    // Save report
    public LabReport saveReport(LabReport report) {
        return labReportRepository.save(report);
    }

    // Delete report
    public void deleteReport(Long id) {
        labReportRepository.deleteById(id);
    }
}
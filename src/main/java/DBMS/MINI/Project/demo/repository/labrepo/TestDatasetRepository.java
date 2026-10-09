package DBMS.MINI.Project.demo.repository.labrepo;

import DBMS.MINI.Project.demo.entity.lab.TestDataset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TestDatasetRepository
        extends JpaRepository<TestDataset, Long> {

    List<TestDataset> findByActiveTrueOrderByCategoryNameAscTestNameAsc();
    Optional<TestDataset> findFirstByTestNameIgnoreCase(String testName);
}
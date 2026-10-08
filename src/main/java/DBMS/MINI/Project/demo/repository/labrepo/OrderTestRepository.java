package DBMS.MINI.Project.demo.repository.labrepo;

import DBMS.MINI.Project.demo.entity.lab.OrderTest;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface OrderTestRepository extends JpaRepository<OrderTest, Long> {

    List<OrderTest> findByOrderOrderId(Long orderId);

}
package DBMS.MINI.Project.demo.Service;

import DBMS.MINI.Project.demo.entity.lab.LabOrder;
import DBMS.MINI.Project.demo.repository.labrepo.LabOrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LabAssistantDashboardService {

    @Autowired
    private LabOrderRepository labOrderRepository;


    public List<LabOrder> getRecentOrders() {

        return labOrderRepository
            .findTop10ByOrderByOrderDateDesc();
    }


    public long getTotalOrders() {

        return labOrderRepository.count();
    }


    public long getPendingOrders() {

        return labOrderRepository
            .countByStatus("PENDING");
    }


    public long getCompletedOrders() {

        return labOrderRepository
            .countByStatus("COMPLETED");
    }


    public long getCancelledOrders() {

        return labOrderRepository
            .countByStatus("CANCELLED");
    }
}
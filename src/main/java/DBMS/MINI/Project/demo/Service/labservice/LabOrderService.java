package DBMS.MINI.Project.demo.Service.labservice;

import DBMS.MINI.Project.demo.entity.lab.*;
import DBMS.MINI.Project.demo.entity.patientlogin.patient;

import DBMS.MINI.Project.demo.repository.*;
import DBMS.MINI.Project.demo.repository.labrepo.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LabOrderService {

    @Autowired
    private LabOrderRepository orderRepository;

    @Autowired
    private OrderTestRepository orderTestRepository;

    @Autowired
    private LabTestRepository testRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private LabAssistantRepository assistantRepository;

    @Autowired
    private PatientRepository patientRepository;


    public List<LabOrder> getAllOrders() {

        return orderRepository.findAll();
    }


    public LabOrder getOrder(Long id) {

        return orderRepository.findById(id)
                .orElseThrow(() ->
                    new RuntimeException(
                        "Lab order not found."
                    ));
    }


    public List<LabTest> getAllTests() {

        return testRepository.findAll();
    }


    public List<Doctor> getAllDoctors() {

        return doctorRepository.findAll();
    }


    public List<LabAssistant> getAllAssistants() {

        return assistantRepository.findAll();
    }


    public List<patient> getAllPatients() {

        return patientRepository.findAll();
    }


    public void createOrder(
            Long patientId,
            Long doctorId,
            Long assistantId,
            Long[] testIds) {


        patient patient =
            patientRepository
            .findById(patientId)
            .orElseThrow(() ->
                new RuntimeException(
                    "Patient not found."
                ));


        LabOrder order =
            new LabOrder();


        order.setOrderNumber(
            "ORD-" +
            System.currentTimeMillis()
        );


        order.setPatient(patient);


        if (doctorId != null) {

            Doctor doctor =
                doctorRepository
                .findById(doctorId)
                .orElseThrow();

            order.setDoctor(doctor);
        }


        if (assistantId != null) {

            LabAssistant assistant =
                assistantRepository
                .findById(assistantId)
                .orElseThrow();

            order.setAssistant(assistant);
        }


        order.setOrderDate(
            LocalDateTime.now()
        );

        order.setStatus("PENDING");

        order.setTotalAmount(
            BigDecimal.ZERO
        );


        orderRepository.save(order);


        if (testIds != null) {

            for (Long testId : testIds) {

                LabTest test =
                    testRepository
                    .findById(testId)
                    .orElseThrow();


                OrderTest orderTest =
                    new OrderTest();


                orderTest.setOrder(order);

                orderTest.setTest(test);

                orderTest.setResultStatus(
                    "PENDING"
                );


                orderTestRepository
                    .save(orderTest);
            }
        }
    }


    public List<OrderTest> getOrderTests(
            Long orderId) {

        return orderTestRepository
                .findByOrderOrderId(orderId);
    }
}
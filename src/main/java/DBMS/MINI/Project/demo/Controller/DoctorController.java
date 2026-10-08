package DBMS.MINI.Project.demo.Controller;

import DBMS.MINI.Project.demo.Service.doctor.DoctorService;
import DBMS.MINI.Project.demo.entity.lab.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/lab/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // Show all doctors
    @GetMapping
    public String getAllDoctors(Model model) {

        model.addAttribute("doctors", doctorService.getAllDoctors());

        return "lab/doctors";
    }

    // Show add doctor form
    @GetMapping("/add")
    public String showAddDoctorForm(Model model) {

        model.addAttribute("doctor", new Doctor());

        return "lab/add-doctor";
    }

    // Save doctor
    @PostMapping("/save")
    public String saveDoctor(@ModelAttribute("doctor") Doctor doctor) {

        doctorService.saveDoctor(doctor);

        return "redirect:/lab/doctors";
    }

    // Delete doctor
    @GetMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable Long id) {

        doctorService.deleteDoctor(id);

        return "redirect:/lab/doctors";
    }
}
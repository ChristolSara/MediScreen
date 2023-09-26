package webApp.Controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import webApp.Models.Patient;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
public class PatientController {
    @GetMapping("/allPatients")
    public String allPatient(@NotNull Model model){
//
//        RestTemplate restTemplate = new RestTemplate();
//       List<Patient> patientList = Collections.singletonList(restTemplate.getForObject("http://localhost:8050/", Patient.class));
//
//        model.addAttribute("patient",patientList);

        return "/index.html";
    }
}

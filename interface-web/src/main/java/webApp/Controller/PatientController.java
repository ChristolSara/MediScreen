package webApp.Controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import webApp.Models.Patient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Controller
public class PatientController {
    @GetMapping("/Patient")
    public String getPatient(@NotNull Model model) throws URISyntaxException {

//        URI uri = new URI("http://localhost:8050/allPatients");
        RestTemplate restTemplate = new RestTemplate();
//        Patient[] patientList = restTemplate.getForObject(uri, Patient[].class);

        String url = "http://localhost:8050/patient/{id}";

        int id = 1;
        Patient patient =restTemplate.getForObject(url, Patient.class,id);
        model.addAttribute("patient",patient);

        return "/index";
    }
    @GetMapping("/PatientList")
    public String allPatient(@NotNull Model model) throws URISyntaxException {

        String  url = "http://localhost:8050/allPatients";
        RestTemplate restTemplate = new RestTemplate();
//        Patient[] patientList = restTemplate.getForObject(uri, Patient[].class);


        Patient[] patientList = restTemplate.getForObject(url, Patient[].class);
        model.addAttribute("patientList",patientList);

        return "/PatientList";
    }


    @PostMapping("/addPatient")
    public String addPatient(Patient patient,Model model){


        String  url = "http://localhost:8050/addPatient";
        RestTemplate restTemplate = new RestTemplate();


        return "/index";
    }


}

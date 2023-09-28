package webApp.Controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import webApp.Models.Patient;
import webApp.enums.Gendre;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Controller
public class PatientController {


    @GetMapping("/Patient/{id}")
    public String getPatient(@NotNull Model model,@PathVariable Integer id) throws URISyntaxException {

        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8050/patient/{id}";

         id = 1;

        Patient patient =restTemplate.getForObject(url, Patient.class,id);

        model.addAttribute("patient",patient);
        return "/index";
    }
    @GetMapping("/PatientList")
    public String allPatient(@NotNull Model model) throws URISyntaxException {

        String  url = "http://localhost:8050/allPatients";
        RestTemplate restTemplate = new RestTemplate();
        Patient[] patientList = restTemplate.getForObject(url, Patient[].class);
        model.addAttribute("patientList",patientList);

        return "/PatientList";
    }
    @GetMapping("/addPatient")
    public String newPatient(Model model){

        Patient patient = new Patient();
        model.addAttribute("Gendre",Gendre.values());
        model.addAttribute("Patient",patient);
        return "/addPatient";
    }


    @PostMapping("/newPatient")
    public String addPatient( @NotNull Patient patient, Gendre gendre) throws ParseException {



        patient.setId(null);

        HttpEntity<Patient> request = new HttpEntity<>(patient);

        String  url = "http://localhost:8050/addPatient";
        RestTemplate restTemplate = new RestTemplate();

        Patient patient2= restTemplate.postForObject(url,request,Patient.class);


        return "/index";
    }


    @DeleteMapping("/delete")
    public String delete( int id, Model model) {
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8050/delete/{id}";
        restTemplate.delete(url,Patient.class,id);

        return "/PatientList";
    }
    @PutMapping("/updatePatient/{id}")
    public String updatePatient(@PathVariable int id, Patient patient,Model model) {

        HttpEntity<Patient> request = new HttpEntity<>(patient);
        RestTemplate restTemplate = new RestTemplate();

        String url = "http://localhost:8050/updatePatient/{id}";

        restTemplate.put(url,Patient.class,id);

        return "/PatientList";
    }




}

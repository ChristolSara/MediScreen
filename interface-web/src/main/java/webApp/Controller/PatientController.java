package webApp.Controller;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import webApp.Models.Patient;
import webApp.Service.PatientService;
import webApp.enums.Gendre;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;



    @GetMapping("/PatientList")
    public String allPatient(@NotNull Model model) throws URISyntaxException {
     List<Patient> patientList = patientService.allPatient();
        model.addAttribute("patientList",patientList);

        return "/PatientList";
    }

    @GetMapping("/Patient/{id}")
    public String getPatient(@NotNull Model model,@PathVariable Integer id) throws URISyntaxException {
      Patient patient=  patientService.getPatient(id);

        model.addAttribute("patient",patient);
        return "/index";

    }
    @GetMapping("/addPatient")
    public String newPatient(Model model){

        Patient patient = new Patient();
        model.addAttribute("Gendre", Gendre.values());
        model.addAttribute("Patient",patient);
        return "/addPatient";

    }


    @PostMapping("/newPatient")
    public String addPatient( @NotNull Patient patient, Gendre gendre) throws ParseException {
         patientService.addPatient(patient,gendre);
        return "/index";
    }


    @GetMapping("/updatePatient/{id}")
    public String updatePatient(@PathVariable("id") int id,Model model) throws URISyntaxException {
        Patient patient1= patientService.getPatient(id);
        model.addAttribute("Gendre", Gendre.values());
        model.addAttribute("Patient",patient1);
        return "/updatePatient";
    }
    @PostMapping("/upPatient")
    public String upPatient(@NotNull Patient patient) throws ParseException {
        patientService.updatePatient(patient);
        return "/index";
    }

    @DeleteMapping("/deletePatient/{id}")
    public String delete(@PathVariable("id") Integer id) {
        patientService.delete(id);
        return "/PatientList";

    }



}

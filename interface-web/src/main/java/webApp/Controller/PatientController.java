package webApp.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import webApp.Models.Patient;
import webApp.Service.PatientService;
import webApp.enums.Gendre;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;

    @GetMapping({"/","/home"})
    public String homePage(Model model) throws URISyntaxException {
        model.addAttribute("patient",new Patient());
        return "/index";
    }
    @GetMapping("/search")
    public String searchPatient(@Valid Patient patient, Model model, BindingResult result) throws URISyntaxException {


        if(patient.getPhoneNumber().isEmpty()){
            String erreur = "Phone Number required";
            model.addAttribute("erreur",erreur);
            return "/index";
        }
        Patient patient1 =patientService.getPatientByNumber(patient.getPhoneNumber());

        if(patient1 == null){
            String erreur = "Phone Number not found";
            model.addAttribute("erreur",erreur);
            return "/index";
       }
        model.addAttribute("patient1",patient1);
        return "/searchResolt";
    }


    @GetMapping("/PatientList")
    public String allPatient( Model model) throws URISyntaxException {
     List<Patient> patientList = patientService.allPatient();
        model.addAttribute("patientList",patientList);

        return "/PatientList";
    }
    @GetMapping("/addPatient")
    public String newPatient(Model model){

        Patient patient = new Patient();
        model.addAttribute("Gendre", Gendre.values());
        model.addAttribute("Patient",patient);
        return "/addPatient";

    }

    @PostMapping("/newPatient")
    public String addPatient( @Valid Patient patient,Model model,BindingResult result ) throws ParseException, URISyntaxException {

         patientService.addPatient(patient);
         List<Patient> patientList = patientService.allPatient();
        model.addAttribute("patientList",patientList);

        return "/PatientList";
    }

    @GetMapping("/updatePatient/{id}")
    public String updatePatient(@PathVariable("id") int id,Model model) throws URISyntaxException {
        Patient patient1= patientService.getPatient(id);
        model.addAttribute("Gendre", Gendre.values());
        model.addAttribute("Patient",patient1);
        return "updatePatient";
    }
    @PostMapping("/upPatient/{id}")
    public String upPatient( Patient patient,@PathVariable Integer id,Model model) throws ParseException {

        patientService.updatePatient(id,patient);
        model.addAttribute("patient",patient);
        return "/index";
    }

    @GetMapping("/deletePatient/{id}")
    public String delete(@PathVariable("id") Integer id,Model model) throws URISyntaxException {
        patientService.delete(id);
        List<Patient> patientList = patientService.allPatient();
        model.addAttribute("patientList",patientList);

        return "/PatientList";

    }



}

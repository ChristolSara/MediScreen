package msDiabete.Controller;

import lombok.AllArgsConstructor;
import msDiabete.Gateway.PatientGateway;
import msDiabete.Models.Patient;
import msDiabete.Service.DiabeteService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class DiabeteController {
    private DiabeteService diabeteService;

    private PatientGateway patientGateway;

    @GetMapping("/diabette/{id}")
    public String getDiabete(@PathVariable Integer id){

        Patient patient = patientGateway.getPatient(id).getBody();

        return diabeteService.diabeteAssess(patient);

    }
}

package webApp.Service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import webApp.Gateway.PatientGateway;
import webApp.Models.Patient;
import webApp.enums.Gendre;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class PatientService {

    @Autowired
    private final PatientGateway patientGateway;

    public List<Patient> allPatient() throws URISyntaxException {
      return patientGateway.allPatient();
    }

    public Patient getPatient(Integer id) {
        return patientGateway.getPatient(id).getBody();
    }

    public Patient getPatientByNumber(String num) {
        return patientGateway.getPatientByNumber(num).getBody();
    }

    public Patient addPatient(Patient patient) throws ParseException {
      return patientGateway.addPatient(patient);
    }

    public void updatePatient( Integer id,Patient patient) {
        patientGateway.updatePatient(id,patient);

    }

    public void delete(Integer id) {
        patientGateway.delete(id);
    }

}

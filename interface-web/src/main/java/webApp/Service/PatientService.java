package webApp.Service;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import webApp.Models.Patient;
import webApp.enums.Gendre;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class PatientService {

    @Autowired
    RestTemplate restTemplate;


    public List<Patient> allPatient() throws URISyntaxException {
        String urlBase = "http://localhost:8050/";
        Patient[] patientList = restTemplate.getForObject(urlBase + "allPatients", Patient[].class);
        return List.of(patientList);
    }

    public ResponseEntity<Patient> getPatient(Integer id) {
        return restTemplate.getForEntity("http://localhost:8050/patient/{id}", Patient.class, id);
    }

    public ResponseEntity<Patient> getPatientByNumber(String num) {
        return restTemplate.getForEntity("http://localhost:8050/patientNum/{num}", Patient.class, num);
    }

    public Patient addPatient(Patient patient, Gendre gendre) throws ParseException {

        HttpEntity<Patient> request = new HttpEntity<>(patient);
        return restTemplate.postForObject("http://localhost:8050/addPatient", request, Patient.class);

    }

    public void updatePatient( Integer id,Patient patient) {

    restTemplate.put("http://localhost:8050/updatePatient/{id}", patient,id);

    }

    public void delete(Integer id) {

        String url = "http://localhost:8050/deletePatient/{id}";
        restTemplate.delete(url, id);

    }

}

package msDiabete.Gateway;

import lombok.AllArgsConstructor;
import msDiabete.Models.Patient;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;
@Component
@AllArgsConstructor
public class PatientGateway {

    private final RestTemplate restTemplate;

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

    public Patient addPatient(Patient patient) throws ParseException {

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

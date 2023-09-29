package mathieu0107.webapp.gateway;

import mathieu0107.webapp.model.Patient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

public class PatientGateway {

    private final RestTemplate restTemplate;

    public PatientGateway(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<Patient> getOnePatient(Integer id) {
        return restTemplate.getForEntity("http://localhost:8080/patient/getOnePatient/{id}", Patient.class, id);
    }

    public ResponseEntity<Patient[]> getAllPatients() {
        return restTemplate.getForEntity("http://localhost:8080/patient/list", Patient[].class);
    }

    public Patient postPatient(Patient patient) {
        return restTemplate.postForObject("http://localhost:8080/patient/create", patient, Patient.class);
    }

    public Patient updatePatient(Integer id, Patient patient) {
        Map<String, Object> pathVariables = new HashMap<>();
        pathVariables.put("id", id);
        return restTemplate.postForObject("http://localhost:8080/patient/update/{id}", patient, Patient.class, pathVariables);
    }

    public void deletePatient(Integer id) {
        restTemplate.delete("patient/delete/{id}", id);
    }
}

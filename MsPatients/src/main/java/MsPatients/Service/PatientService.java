package MsPatients.Service;

import MsPatients.Models.Patient;
import MsPatients.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {
    
    @Autowired
    private PatientRepository patientRepository;


    public Patient addPatient(Patient patient) {
        Patient patient1 = patientRepository.save(patient);
        return patient1;
    }

    public List<Patient> allPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList;
    }


    public Patient getPatientById(Integer id) {
        return patientRepository.getPatientById(id);
    }

    public Patient updatePatient(Patient patient) {

        return patientRepository.save(patient);
    }

    public Patient getPatientByPhoneNumber(String number) {
        return patientRepository.getPatientByPhoneNumber(number);
    }

    public void deletePatientById(Integer id) {
        patientRepository.deleteById(id);
    }
}

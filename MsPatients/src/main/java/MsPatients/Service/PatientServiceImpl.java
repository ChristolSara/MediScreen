package MsPatients.Service;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
import MsPatients.Repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PatientServiceImpl implements IPatientService {

    private PatientRepository patientRepository;


    @Override
    public Patient addPatient(Patient patient) throws PatientAlreadyExistsException {
        log.info("save new patient");


        Patient patient1 = patientRepository.getPatientByPhoneNumber(patient.getPhoneNumber());

        if (patient1.getPhoneNumber().equals(patient.getPhoneNumber())) {
            throw new PatientAlreadyExistsException("Patient with id " + patient.getId() + " already exist");
        }


        if (patient.equals(null)) {
             patientRepository.save(patient);
        }
        return patient;

    }

    @Override
    public List<Patient> allPatients() {
        log.info("get all patients");
        List<Patient> patientList = patientRepository.findAll();
        return patientList;
    }


    @Override
    public Patient getPatientById(Integer id) throws PatientNotFoundException {
        log.info("get patient by id");
        Patient patient = patientRepository.getPatientById(id);
        if (patient == null) {
            throw new PatientNotFoundException("Patient with Id " + id + " not found");
        }
        return patient;
    }

    @Override
    public Patient updatePatient(Integer id, Patient patient) {
        log.info("update patient");

        patient.setId(id);
        return patientRepository.save(patient);
    }

    @Override
    public Patient getPatientByPhoneNumber(String number) throws PatientNotFoundException {
        log.info("get patient by number");
        return patientRepository.getPatientByPhoneNumber(number);
    }

    @Override
    public void deletePatientById(Integer id) throws PatientNotFoundException {
        log.info("delete patient");
        patientRepository.deleteById(id);
    }
}

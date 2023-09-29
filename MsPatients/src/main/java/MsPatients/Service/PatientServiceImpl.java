package MsPatients.Service;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
import MsPatients.Repository.PatientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
public class PatientServiceImpl implements IPatientService {
    private PatientRepository patientRepository;

    @Override
    public Patient addPatient(Patient patient) throws PatientAlreadyExistsException {
        log.info("save new patient");

        if (patientRepository.existsByPhoneNumber(patient.getPhoneNumber())) {
            throw new PatientAlreadyExistsException("Patient already exist");
        }
        return patientRepository.save(patient);
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
        Optional<Patient> patient = patientRepository.findById(id);
        if (patient.isEmpty()) {
            throw new PatientNotFoundException("Patient with Id " + id + " not found");
        }
        Patient patient1 = patient.get();
        return patient1;
    }

    @Override
    public Patient updatePatient(Integer id,Patient patient) throws PatientAlreadyExistsException {
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

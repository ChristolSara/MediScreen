package MsPatients.Service;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
import MsPatients.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientServiceImpl implements IPatientService{
    
    @Autowired
    private PatientRepository patientRepository;


    @Override
    public Patient addPatient(Patient patient) throws PatientAlreadyExistsException {
        List<Patient> patientList = patientRepository.findAll();

        if (patientList.contains(patient)) {
            throw new PatientAlreadyExistsException("Patient already exist");
        }
        Patient patient1 = patientRepository.save(patient);
        return patient1;
    }

    @Override
    public List<Patient> allPatients() {
        List<Patient> patientList = patientRepository.findAll();
        return patientList;
    }


    @Override
    public Patient getPatientById(Integer id) throws PatientNotFoundException {
        return patientRepository.getPatientById(id);
    }

    @Override
    public Patient updatePatient(Patient patient) {

        return patientRepository.save(patient);
    }
    @Override
    public Patient getPatientByPhoneNumber(String number) throws PatientNotFoundException{
        return patientRepository.getPatientByPhoneNumber(number);
    }

    @Override
    public void deletePatientById(Integer id) throws PatientNotFoundException {
        patientRepository.deleteById(id);
    }
}

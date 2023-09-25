package MsPatients.Service;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;

import java.util.List;

public interface IPatientService {
    public Patient addPatient(Patient patient) throws PatientAlreadyExistsException;
    public List<Patient> allPatients();
    public Patient getPatientById(Integer id) throws PatientNotFoundException;
    public Patient updatePatient(Integer id,Patient patient);
    public Patient getPatientByPhoneNumber(String number) throws PatientNotFoundException;
    public void deletePatientById(Integer id) throws PatientNotFoundException;
}

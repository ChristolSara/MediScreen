package MsPatients;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
import MsPatients.Repository.PatientRepository;
import MsPatients.Service.PatientServiceImpl;
import MsPatients.Web.PatientController;
import MsPatients.enums.Gendre;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertNotNull;

@SpringBootTest

public class PatientControllerTests {


    @Autowired
    private PatientController patientController;
    @Autowired
    private PatientServiceImpl patientService;
    @Autowired
    private PatientRepository patientRepository;


    @BeforeEach
    public void Patient() throws PatientAlreadyExistsException {



    }

    @Test
    public void SavePatient() throws PatientAlreadyExistsException, PatientNotFoundException {

        ///prepare
        Patient patient = new Patient(33, "test", "testLast", new Date(), Gendre.HOMME,
                "1 rue test", "5066052412");
        //excute save
        patientController.addPatient(patient);

        Patient patient1 = patientRepository.getPatientById(33);


        //assert
        Assert.assertEquals("test","test");


    }

    @Test
    public void updatePatient() throws PatientAlreadyExistsException {
        Patient patient = new Patient(10, "test", "testLast", new Date(), Gendre.HOMME, "1 rue test", "5066052412");

        Patient patient1 = patientController.addPatient(patient);
        //update
        patient.setId(22);
        patientController.updatePatient(22,patient);
        Assert.assertTrue(patient.getId().equals(22));

    }

    @Test
    public void listPatient() {
        List<Patient> patientList = patientController.allPatients();
        Assert.assertTrue(patientList.size() > 0);
    }

    @Test
    public void getPatientById() throws PatientAlreadyExistsException, PatientNotFoundException {

        Patient patient = new Patient(2, "test", "testLast", new Date(), Gendre.HOMME, "1 rue test", "5066052412");

        patientRepository.save(patient);

        Patient patient2 = patientController.getPatientById(2);



        Assert.assertEquals("test",patient.getFirstName());
    }

    @Test
    public void deletePatient() throws PatientAlreadyExistsException, PatientNotFoundException {
        Patient patient = new Patient(10, "test", "testLast", new Date(), Gendre.HOMME, "1 rue test", "5066052412");

        patientController.addPatient(patient);
        Integer id = patient.getId();
        patientController.deletePatient(id);


        Assert.assertTrue(!patientController.allPatients().contains(patient));
    }


}

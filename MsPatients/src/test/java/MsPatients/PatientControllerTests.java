package MsPatients;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
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


    @BeforeEach
    public void Patient() throws PatientAlreadyExistsException {



    }

    @Test
    public void SavePatient() throws PatientAlreadyExistsException, PatientNotFoundException {

        ///prepare
        Patient patient = new Patient(null, "test", "testLast", new Date(), Gendre.HOMME,
                "1 rue test", "5066052412");
        //excute save
        patientController.addPatient(patient);
       Patient patient1 = patientService.getPatientByPhoneNumber("5066052412");
        //assert
        Assert.assertEquals(patient.getFirstName(),patient1.getFirstName());


    }

    @Test
    public void updatePatient() {
        Patient patient = new Patient(10, "test", "testLast", new Date(), Gendre.HOMME, "1 rue test", "5066052412");

        //update
        patient.setId(22);
        patientController.updatePatient(patient);
        Assert.assertTrue(patient.getId().equals(22));

    }

    @Test
    public void listPatient() {
        List<Patient> patientList = patientController.allPatients();
        Assert.assertTrue(patientList.size() > 0);
    }

    @Test
    public void getPatientById() throws PatientAlreadyExistsException, PatientNotFoundException {

        Patient patient = new Patient(10, "test", "testLast", new Date(), Gendre.HOMME, "1 rue test", "5066052412");

        patientController.addPatient(patient);

         Patient patient1=patientService.getPatientById(10);

        Assert.assertEquals(patient.getFirstName(),patient1.getFirstName());
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

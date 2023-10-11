package MsPatients;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;

import MsPatients.Controller.PatientController;
import MsPatients.enums.Gendre;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

@SpringBootTest
public class ExceptionsTests {
    @Autowired
   private PatientController patientController;

    @Test
    public void ExceptionPatientNotFoundTest() throws Exception {
        Exception exception = assertThrows(PatientNotFoundException.class, () -> {
            patientController.getPatientById(100);
        });
        String expectedMessage = "Patient with Id 100 not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

    @Test
    public void PatientAlreadyExistsException() throws Exception {

        Exception exception = assertThrows(PatientAlreadyExistsException.class, () -> {
            Patient patient=patientController.getPatientById(4);
            patientController.addPatient(patient);
        });
        String expectedMessage = "Patient already exist";
        String actualMessage = exception.getMessage();

        Assert.assertTrue(actualMessage.contains(expectedMessage));

    }


    @Test
    void Patient() throws PatientAlreadyExistsException, PatientNotFoundException {
        Patient patientTest = new Patient(null, "test", "testLast", new Date(), Gendre.HOMME, "1 test ", "0655663322");
        //test save
        Patient injectedPatient = patientController.addPatient(patientTest);
        //assert
        Assert.assertNotNull(injectedPatient);// Check if the result is not null
        //test allPatient
        List<Patient> patientList = patientController.allPatients();
        Assert.assertTrue(patientList.contains(injectedPatient));
        //test update Patient + get by id
        Patient patientUpdated = patientController.getPatientById(injectedPatient.getId());
        patientUpdated.setAdress("123");
        patientController.updatePatient(patientUpdated.getId(), patientUpdated);

        Assert.assertEquals(patientUpdated.getAdress(), "123");
        Assert.assertEquals(patientUpdated.getFirstName(), "test");

        //test delete
        patientController.deletePatient(patientUpdated.getId());
        Assert.assertTrue(!patientController.allPatients().contains(patientTest));
    }


}

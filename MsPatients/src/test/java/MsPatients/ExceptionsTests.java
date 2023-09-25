package MsPatients;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
import MsPatients.Service.PatientServiceImpl;

import MsPatients.Web.PatientController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
            Patient patient=patientController.getPatientById(1);
            patientController.addPatient(patient);
        });
        String expectedMessage = "Patient with id 1 already exist";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

    }

}

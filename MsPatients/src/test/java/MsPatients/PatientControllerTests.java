package MsPatients;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
import MsPatients.Controller.PatientController;
import MsPatients.Repository.PatientRepository;
import MsPatients.Service.PatientServiceImpl;
import MsPatients.enums.Gendre;
import lombok.AllArgsConstructor;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@SpringBootTest
public class PatientControllerTests {
    @Autowired
    PatientController patientController;


    @Test
    void SavePatient() throws PatientAlreadyExistsException {
        Patient patientTest = new Patient(null,"test","testLast",new Date(),Gendre.HOMME,"1 test ","0655663322");
        //excute save
        patientController.addPatient(patientTest);
        //assert
        Assert.assertNotNull(patientTest);// Check if the result is not null
        Assert.assertTrue(patientController.allPatients().contains(patientTest));

        patientController.delete(patientTest);
    }

    @Test
    public void updatePatient() throws ParseException, PatientAlreadyExistsException, PatientNotFoundException {
        Patient patientTest = new Patient(null,"test","testLast",new Date(),Gendre.HOMME,"1 test ","0655663322");


//        update
        patientController.addPatient(patientTest);
        Patient patient1 = patientController.getPatientByPhoneNumber(patientTest.getPhoneNumber());
        patient1.setAdress("123");
        patientController.updatePatient(patient1.getId(), patient1);
        Assert.assertEquals(patient1.getAdress(), "123");
        Assert.assertEquals(patient1.getFirstName(), "test");
        patientController.delete(patient1);
    }

    @Test
    public void listPatient() {


        List<Patient> patientList = patientController.allPatients();

        Assert.assertTrue(patientList.size() >= 0);

    }

    @Test
    public void getPatientById() throws PatientAlreadyExistsException, PatientNotFoundException {
        Patient patientTest = new Patient(null,"test","testLast",new Date(),Gendre.HOMME,"1 test ","0655663322");


        //excute
        Patient patientInjected = patientController.addPatient(patientTest);

        //assert
        Assert.assertEquals("testLast", patientInjected.getLastName());
        patientController.delete(patientInjected);
    }

    @Test
    public void deletePatient() throws PatientAlreadyExistsException, PatientNotFoundException {
        Patient patientTest = new Patient(null,"test","testLast",new Date(),Gendre.HOMME,"1 test ","0655663322");


        patientController.addPatient(patientTest);


        patientController.deletePatient(patientTest.getId());


        Assert.assertTrue(!patientController.allPatients().contains(patientTest));
    }


}

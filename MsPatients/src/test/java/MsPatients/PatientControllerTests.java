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


    private static Patient patientTest = new Patient();
    @BeforeEach
     public void injectPatient()  {
        patientTest.setId(13333);

      patientTest.setPhoneNumber("0605889977");
      patientTest.setFirstName("terst");
      patientTest.setBirthday(new Date());
      patientTest.setLastName("testLast");
      patientTest.setGenre(Gendre.FEMME);

    }
    @AfterEach
    public void delPatient() throws PatientAlreadyExistsException, PatientNotFoundException {

        patientController.deletePatient(13333);

    }
    @Test
     void SavePatient() throws PatientAlreadyExistsException, PatientNotFoundException, ParseException {


        ///prepare

        patientController.addPatient(patientTest);
        //excute save

        Patient patient1 = patientController.getPatientByPhoneNumber(patientTest.getPhoneNumber());
        //assert
        Assert.assertNotNull(patientTest);// Check if the result is not null
        Assert.assertEquals("terst",patient1.getFirstName());
    }

    @Test
    public void updatePatient() throws ParseException, PatientAlreadyExistsException, PatientNotFoundException {

//        update
        patientController.addPatient(patientTest);
        Patient patient1 = patientController.getPatientByPhoneNumber(patientTest.getPhoneNumber());
        patient1.setPhoneNumber("0766991155");
        patientController.updatePatient(patient1.getId(),patient1);
        Assert.assertEquals(patient1.getPhoneNumber(), "0766991155");
        Assert.assertEquals(patient1.getFirstName(), "terst");
    }

    @Test
    public void listPatient() {
        List<Patient> patientList = patientController.allPatients();
        Assert.assertTrue(patientList.size() > 0);
    }

    @Test
    public void getPatientById() throws PatientAlreadyExistsException, PatientNotFoundException {

        //preparation

        patientController.addPatient(patientTest);

        Patient patient2 = patientController.getPatientById(patientTest.getId());
        //assert
        Assert.assertEquals("0605889977",patientTest.getPhoneNumber());
    }

    @Test
    public void deletePatient() throws PatientAlreadyExistsException, PatientNotFoundException {

        patientController.addPatient(patientTest);

        patientController.deletePatient(patientTest.getId());


        Assert.assertTrue(!patientController.allPatients().contains(patientTest));
    }


}

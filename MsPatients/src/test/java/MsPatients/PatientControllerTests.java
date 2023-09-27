package MsPatients;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
import MsPatients.Controller.PatientController;
import MsPatients.enums.Gendre;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



@SpringBootTest

public class PatientControllerTests {
    @Autowired
    private PatientController patientController;
    @BeforeEach
    public void Patient() throws PatientAlreadyExistsException {
    }
    @Test
    public void SavePatient() throws PatientAlreadyExistsException, PatientNotFoundException, ParseException {

        ///prepare
        Date psteDate = new SimpleDateFormat("yyyy-MM-dd").parse("1900-10-20");
        Patient patient = new Patient(null, "save", "saveLast", psteDate ,Gendre.HOMME,
                "1 rue test", "568899");
        //excute save
        patientController.addPatient(patient);

        Patient patient1 = patientController.getPatientByPhoneNumber(patient.getPhoneNumber());
        //assert
        Assert.assertNotNull(patient);// Check if the result is not null
        Assert.assertEquals("save",patient1.getFirstName());
    }

    @Test
    public void updatePatient() throws ParseException, PatientAlreadyExistsException, PatientNotFoundException {
        Date psteDate = new SimpleDateFormat("yyyy-MM-dd").parse("1900-10-20");
        Patient patient = new Patient(null, "awa", "chout", psteDate, Gendre.AUTRE, "2 Rue John", "028965");
//        update
        patientController.addPatient(patient);
        Patient patient1 = patientController.getPatientByPhoneNumber("028965");
        patient1.setPhoneNumber("077777");
        patientController.updatePatient(patient1.getId(),patient1);
        Assert.assertEquals(patient1.getPhoneNumber(), "077777");
        Assert.assertEquals(patient1.getFirstName(), "awa");
    }

    @Test
    public void listPatient() {
        List<Patient> patientList = patientController.allPatients();
        Assert.assertTrue(patientList.size() > 0);
    }

    @Test
    public void getPatientById() throws PatientAlreadyExistsException, PatientNotFoundException {

        //preparation
        Patient patient = new Patient(null, "test", "testLast", new Date(), Gendre.HOMME, "1 rue test", "5066052412");
        patientController.addPatient(patient);

        Patient patient2 = patientController.getPatientById(patient.getId());
        //assert
        Assert.assertEquals("5066052412",patient.getPhoneNumber());
    }

    @Test
    public void deletePatient() throws PatientAlreadyExistsException, PatientNotFoundException {
        Patient patient = new Patient(null, "test", "testLast", new Date(), Gendre.HOMME, "1 rue test", "5066052412");

        patientController.addPatient(patient);

        patientController.deletePatient(patient.getId());


        Assert.assertTrue(!patientController.allPatients().contains(patient));
    }


}

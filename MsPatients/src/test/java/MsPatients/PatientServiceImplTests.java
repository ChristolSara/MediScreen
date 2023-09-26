package MsPatients;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
import MsPatients.Service.PatientServiceImpl;
import MsPatients.Web.PatientController;
import MsPatients.enums.Gendre;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class PatientServiceImplTests {
    @Autowired
    private PatientServiceImpl patientService;

    @Before
    public void Patient() {

    }

    @Test
    public void SavePatient() throws PatientAlreadyExistsException, PatientNotFoundException {
        //préparation
        Patient patient = new Patient(45, "test", "testLast", new Date(), Gendre.HOMME, "1 rue ", "1222222");
        //excution save
        patientService.addPatient(patient);
        //assertion
        Assert.assertNotNull(patient);// Check if the result is not null
        Assert.assertTrue(patient.getFirstName().equals("test"));

    }

    @Test
    public void updatePatient() throws ParseException, PatientAlreadyExistsException, PatientNotFoundException {
        Date psteDate = new SimpleDateFormat("yyyy-MM-dd").parse("1900-10-20");
        Patient patient = new Patient(null, "awa", "chout", psteDate, Gendre.AUTRE, "2 Rue John", "028965");
//        update
        patientService.addPatient(patient);
        Patient patient1 = patientService.getPatientByPhoneNumber("028965");
        patient1.setPhoneNumber("077777");
        patientService.updatePatient(patient1.getId(),patient1);
        Assert.assertEquals(patient1.getPhoneNumber(), "077777");
        Assert.assertEquals(patient1.getFirstName(), "awa");
    }

    @Test
    public void listPatient() {
        List<Patient> patientList = patientService.allPatients();
        Assert.assertTrue(patientList.size() > 0);
    }

    @Test
    public void getPatientById() throws PatientAlreadyExistsException, PatientNotFoundException {
         //preparation
        Patient patient = patientService.getPatientById(1);

        String name = patient.getFirstName();
        Assert.assertTrue(name.equals("jean"));
    }

    @Test
    public void deletePatient() throws PatientAlreadyExistsException, PatientNotFoundException {
        //préparation
       Patient patient = patientService.getPatientById(4);
        //excution
        patientService.deletePatientById(patient.getId());
        //assertion
        Assert.assertTrue(!patientService.allPatients().contains(patient));
    }


}

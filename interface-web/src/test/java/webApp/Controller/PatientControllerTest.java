package webApp.Controller;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.ui.Model;
import webApp.Models.Patient;
import webApp.Service.PatientService;
import webApp.enums.Gendre;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PatientControllerTest {
    @Autowired
    private PatientService patientService;
    @Autowired
    private  PatientController patientController;



    @Test
    void homePage() throws URISyntaxException {

;
    }

    @Test
    void searchPatient() throws ParseException {

        Date psteDate = new SimpleDateFormat("yyyy-MM-dd").parse("1900-10-20");
        Patient patient = new Patient(null, "sara", "mabrou", psteDate, Gendre.HOMME, "2 rue rass", "0145886933");

        //excution
        patientService.addPatient(patient,patient.getGenre());
        String name =  patientService.getPatientByNumber(patient.getPhoneNumber()).getBody().getFirstName();

       //assertion
        Assert.assertEquals(patient.getFirstName(),name);
    }

    @Test
    void allPatient() throws URISyntaxException {
        List<Patient> patientList = patientService.allPatient();

        Assert.assertTrue(patientList.size()>0);
    }

    @Test
    void newPatient() {
    }

    @Test
    void addPatient() throws ParseException {
        Date psteDate = new SimpleDateFormat("yyyy-MM-dd").parse("1988-12-05");
        Patient patient = new Patient(null, "abdel", "mabrou", psteDate, Gendre.HOMME, "2 rue rass", "0202025663");

        //excution
        patientService.addPatient(patient,Gendre.HOMME);
        String num =  patientService.getPatientByNumber(patient.getPhoneNumber()).getBody().getPhoneNumber();

        //assertion
        Assert.assertEquals("02020",num);

    }

    @Test
    void updatePatient() {
    }

    @Test
    void upPatient() {
    }

    @Test
    void delete() throws ParseException, URISyntaxException {

        Date psteDate = new SimpleDateFormat("yyyy-MM-dd").parse("1988-12-05");
        Patient patient = new Patient(null, "abdel", "mabrou", psteDate, Gendre.FEMME, "2 rue rass", "0656565600");
        Patient patientCreated = patientService.addPatient(patient,Gendre.FEMME);
        List<Patient> patientList = patientService.allPatient();
        //excution
        patientService.delete(patientCreated.getId());
        List<Patient> patientList2 = patientService.allPatient();
        Assert.assertTrue(patientList.contains(patientCreated));
        Assert.assertTrue(!patientList.contains(patient));

    }
}
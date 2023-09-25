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

import java.util.Date;
import java.util.List;
@SpringBootTest
public class PatientServiceImplTests {






        @Autowired
        private PatientServiceImpl patientService;
        @Autowired
        private PatientController patientController;


        @Before
        public void Patient() {


        }

        @Test
        public void SavePatient() throws PatientAlreadyExistsException {
            Patient patient = new Patient(10, "test", "testLast", new Date(), Gendre.HOMME, "1 rue test", "5066052412");

            //save
            patientService.addPatient(patient);
            Assert.assertNotNull(patient.getAdress());
            Assert.assertTrue(patient.getFirstName().equals("test"));

        }

        @Test
        public void updatePatient() {
            Patient patient = new Patient(10, "test", "testLast", new Date(), Gendre.HOMME, "1 rue test", "5066052412");

            //update
            patient.setId(22);
            patientService.updatePatient(22,patient);
            Assert.assertTrue(patient.getId().equals(22));

        }

        @Test
        public void listPatient() {
            List<Patient> patientList = patientService.allPatients();
            Assert.assertTrue(patientList.size() > 0);
        }

    @Test
    public void getPatientById() throws PatientAlreadyExistsException, PatientNotFoundException {
       Patient patient = patientService.getPatientById(1);

        String name = patient.getFirstName();
        Assert.assertTrue(name.equals("jean"));
    }

        @Test
        public void deletePatient() throws PatientAlreadyExistsException, PatientNotFoundException {
            Patient patient = new Patient(10, "test", "testLast", new Date(), Gendre.HOMME, "1 rue test", "5066052412");

            patientService.addPatient(patient);
            Integer id = patient.getId();
            patientService.deletePatientById(id);


            Assert.assertTrue(!patientService.allPatients().contains(patient));
        }


    }

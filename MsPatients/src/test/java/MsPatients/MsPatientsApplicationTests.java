package MsPatients;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
import MsPatients.Repository.PatientRepository;
import MsPatients.Web.PatientController;
import MsPatients.enums.Gendre;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;


@SpringBootTest
class MsPatientsApplicationTests {
	@Autowired
	private PatientController patientController;
	@Autowired
	private PatientRepository patientRepository;

	@Test
	void contextLoads() throws PatientAlreadyExistsException, PatientNotFoundException {
		Patient patient = new Patient(22,"amir","amirLast",new Date(), Gendre.HOMME,"1 paris 92000","0646445224");


		Patient patient1= new Patient();
		patient1.setId(15);
		patient1.setLastName("manon");
		patient1.setLastName("manonLast");
		patient1.setGenre(Gendre.FEMME);
		patient1.setAdress("1 rue jut");
		patient1.setPhoneNumber(patient.randomNumber());
		patient1.setBirthday(new Date());


		List<Patient> patientList = patientController.allPatients();
		patientList.add(patient);
		patientList.add(patient1);


		Patient patient2 = new Patient();

		Patient patient3 = patientController.getPatientById(1);
		patient2.setPhoneNumber(patient3.getPhoneNumber());
		patient2.setBirthday(patient3.getBirthday());
		patient2.setId(patient3.getId());
		patient2.setLastName(patient3.getLastName());
		patient2.setGenre(patient3.getGenre());
		patient2.setAdress(patient3.getAdress());
		patient2.setFirstName(patient3.getFirstName());




		Assert.assertTrue(patientList.size() > 0);
		Assert.assertTrue(String.valueOf(true), patientList.contains(patient));
		Assert.assertTrue(String.valueOf(true), patientList.contains(patient1));
		Assert.assertEquals(patient3.toString(),patient2.toString());
		Assert.assertEquals(patient3.hashCode(),patient2.hashCode());
		Assert.assertEquals(patient.getPhoneNumber().length(),10);



	}
	@Test
	public void givenPatientObject_whenSave_thenReturnSavedPatient(){

		//given - precondition or setup
		Patient employee = Patient.builder()
				.firstName("Ramesh")
				.lastName("Ramesh")
				.id(25)
				.birthday(new Date())
				.adress("marseille 13001")
				.genre(Gendre.HOMME)
				.phoneNumber("05660546")
				.build();
		// when - action or the behaviour that we are going test
		Patient savedEmployee = patientRepository.save(employee);

		// then - verify the output
		Assertions.assertTrue(!((savedEmployee) == null));
		Assert.assertEquals(true, savedEmployee.getId() >0);
	}



}

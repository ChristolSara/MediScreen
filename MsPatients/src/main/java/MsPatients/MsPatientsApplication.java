package MsPatients;

import MsPatients.Models.Patient;
import MsPatients.Repository.PatientRepository;
import MsPatients.enums.Gendre;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


import java.util.Arrays;
import java.util.Date;



@SpringBootApplication
@AllArgsConstructor
public class MsPatientsApplication {


	public static void main(String[] args) {
		SpringApplication.run(MsPatientsApplication.class, args);
	}
	//@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args -> {


			for (String name : Arrays.asList("jean", "sara", "ilyane", "rania", "awatef")) {
				Patient patient = new Patient();
				patient.setFirstName(name);
				patient.setLastName(name + "Last");
				patient.setBirthday(new Date());
				patient.setGenre(Gendre.randomGendre());
				patient.setPhoneNumber("0"+patient.randomNumber());
				patient.setAdress("1 rue " + name);

				patientRepository.save(patient);
			}

		};
	}




}

package MsPatients.Controller;

import MsPatients.Exceptions.PatientAlreadyExistsException;
import MsPatients.Exceptions.PatientNotFoundException;
import MsPatients.Models.Patient;
import MsPatients.Service.PatientServiceImpl;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
@AllArgsConstructor
public class PatientController {


    private final PatientServiceImpl patientService;


    @PostMapping("/addPatient")
    public Patient addPatient(@RequestBody Patient patient) throws PatientAlreadyExistsException {
        return patientService.addPatient(patient);
    }

    @GetMapping("/allPatients")
    public List<Patient> allPatients(){
        return patientService.allPatients();
    }

    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable Integer id)throws PatientNotFoundException {

        return patientService.getPatientById(id);
    }

    @GetMapping("/patientNum/{number}")
    public Patient getPatientByPhoneNumber(@PathVariable String number)throws PatientNotFoundException {
        return patientService.getPatientByPhoneNumber(number);
    }


    @PutMapping("/updatePatient/{id}")
    public Patient updatePatient(@PathVariable Integer id ,@RequestBody Patient patient) throws PatientAlreadyExistsException {
        return patientService.updatePatient(id,patient);
    }

   @DeleteMapping("/deletePatient/{id}")
    public void deletePatient(@PathVariable Integer id)throws PatientNotFoundException{
        patientService.deletePatientById(id);
    }
    @DeleteMapping("/delete")
    public void delete(Patient patient){
        patientService.delete(patient);
    }

}

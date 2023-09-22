package MsPatients.Repository;

import MsPatients.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
    Patient getPatientById(Integer id);

    Patient getPatientByPhoneNumber(String number);
}

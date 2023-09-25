package MsPatients.Repository;

import MsPatients.Models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {


    @Query("SELECT p FROM Patient p WHERE p.phoneNumber =:number")
    Patient getPatientByPhoneNumber(@Param("number")String number);


    Patient getPatientById(Integer id);
}

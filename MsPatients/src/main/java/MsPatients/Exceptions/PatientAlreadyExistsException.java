package MsPatients.Exceptions;

public class PatientAlreadyExistsException extends Exception {
    public PatientAlreadyExistsException(String patientAlreadyExist) {
        super(patientAlreadyExist);
    }
}

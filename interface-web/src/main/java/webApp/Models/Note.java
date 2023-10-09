package webApp.Models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Note {

    private String id;
    private  Integer PatientId;
    private String note;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date noteDate;


}

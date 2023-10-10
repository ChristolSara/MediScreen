package webApp.Models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Note {

    private String id;
    private  Integer patientId;
    private String note;


    private Date noteDate;


}

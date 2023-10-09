package MsNotes.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Document("note")
@AllArgsConstructor @NoArgsConstructor @Data
public class Note {
    @Id
    private String id;
    private  Integer patientId;
    private String note;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date noteDate;



}

package MsNotes.Models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Document("note")
@AllArgsConstructor @NoArgsConstructor @Getter @Setter
public class Note {
    @Id
    private String id;
    private  Integer patientId;
    private String note;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date noteDate;



}

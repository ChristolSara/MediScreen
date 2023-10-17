package msDiabete.Models;

import lombok.Data;

import java.util.Date;
@Data
public class Note {

    private String id;
    private  Integer patientId;
    private String note;


    private Date noteDate;

}

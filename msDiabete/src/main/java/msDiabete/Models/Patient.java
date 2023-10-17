package msDiabete.Models;

import lombok.Data;
import msDiabete.enums.Gendre;

import java.util.Date;
@Data
public class Patient {

    private Integer id;
    private String firstName;
    private String lastName;

    private Date birthday;
    private Gendre genre;
    private String adress;


    private String phoneNumber;

}

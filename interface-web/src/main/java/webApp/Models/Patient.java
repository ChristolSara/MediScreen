package webApp.Models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import webApp.enums.Gendre;


import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class Patient {
    private Integer id;
    private String firstName;
    private String lastName;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private Gendre genre;
    private String adress;

    private String phoneNumber;

}

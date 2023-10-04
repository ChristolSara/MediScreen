package webApp.Models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import webApp.enums.Gendre;


import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class Patient {
    private Integer id;
    @NotEmpty(message = "Patient's firstName cannot be empty.")
    private String firstName;
    @NotEmpty(message = "Patient's lastName cannot be empty.")
    private String lastName;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
    private Gendre genre;
    private String adress;


    private String phoneNumber;


}

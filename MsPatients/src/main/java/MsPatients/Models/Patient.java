package MsPatients.Models;

import MsPatients.enums.Gendre;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Random;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date birthday;
    private Gendre genre;
    private String adress;
    @NotBlank(message = "phoneNumber is required")
    private String phoneNumber;



    public String randomNumber()
    {
        int set1,set2, set3; //sequence 2 and 3 of the phone number

        Random generator = new Random();

        set1 = generator.nextInt(699) +200;
                // Sequence two of phone number
        // the plus 100 is so there will always be a 3 digit number
        // randomize to 643 because 0 starts the first placement so if i randomized up to 642 it would only go up yo 641 plus 100
        // and i used 643 so when it adds 100 it will not succeed 742
        set2 = generator.nextInt(643) + 100;

        //Sequence 3 of numebr
        // add 1000 so there will always be 4 numbers
        //8999 so it wont succed 9999 when the 1000 is added
        set3 = generator.nextInt(899) + 100;

        return String.valueOf (  set1+   " " + set2 + " " + set3 );

    }
}

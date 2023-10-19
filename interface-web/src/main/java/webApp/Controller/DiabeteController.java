package webApp.Controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import webApp.Gateway.DiabeteGateway;

@Controller
@AllArgsConstructor
public class DiabeteController {
    private DiabeteGateway diabeteGateway;

    @GetMapping("/diabete/{patientId}")
    public String addNote (@PathVariable("patientId")Integer patientId, Model model){

       ResponseEntity<String> response = diabeteGateway.getDiabete(patientId);
         String diabete = response.getBody();

         String p = null;
         if(diabete.contains("None")){
             p ="0,100";
           
         }else if(diabete.contains("Borderline") ){
           p = "10,100";

         }else if(diabete.contains("Early onset") ){
             p = "30,100";
            
         }else if(diabete.contains("In Danger") ){
            p = "90,100";
            
         }

        model.addAttribute("p" ,p);
        model.addAttribute("patientId",patientId);
        model.addAttribute("diabete",diabete);
        return "diabeteResult";

    }



    @GetMapping("/errPage")
    public String errPage(){
        return "ErreurPage";
    }
}

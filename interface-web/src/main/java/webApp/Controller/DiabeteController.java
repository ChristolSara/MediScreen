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

         if(diabete =="None"){
             String p = "0";
             model.addAttribute("p",p);
             model.addAttribute("patientId",patientId);
             model.addAttribute("diabete",diabete);
         }else if(diabete =="Borderline"){
             String p = "10";
             model.addAttribute("p",p);
             model.addAttribute("patientId",patientId);
             model.addAttribute("diabete",diabete);

         }else if(diabete =="Early onset"){
             String p = "30";
             model.addAttribute("p",p);
             model.addAttribute("patientId",patientId);
             model.addAttribute("diabete",diabete);

         }else if(diabete =="In Danger"){
             String p = "90";
             model.addAttribute("p",p);
             model.addAttribute("patientId",patientId);
             model.addAttribute("diabete",diabete);

         }
        return "diabeteResult";
    }



    @GetMapping("/errPage")
    public String errPage(){
        return "ErreurPage";
    }
}

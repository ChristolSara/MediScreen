package webApp.Controller;

import lombok.AllArgsConstructor;
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

       String diabete= diabeteGateway.getDiabete(patientId).toString();

        model.addAttribute("patientId",patientId);
        model.addAttribute("diabete",diabete);
        return "diabeteResult";
    }
}

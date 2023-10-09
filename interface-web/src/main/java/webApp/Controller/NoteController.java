package webApp.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import webApp.Models.Note;
import webApp.Service.NotesService;
import webApp.Service.PatientService;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("notes")
public class NoteController {

    private final NotesService notesService;
    private final PatientService patientService;

    @GetMapping("/add/{patientId}")
    public String addNote (@PathVariable("patientId")Long patientId, Model model, Note note){

        model.addAttribute("patientId",patientId);
        return "notes/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid Note note, BindingResult result,Model model) throws ParseException {
        if(!result.hasErrors()){
            note.setNoteDate(new Date());
            notesService.addNote(note);
            model.addAttribute("notes",notesService.getAllNotesByPatientId(note.getPatientId()));
        }
        return "notes/add";
    }



    @GetMapping("/NoteList/{patientId}")
    public  String getAllNotesByPatientId(@PathVariable("patientId")Integer patientId,Model model){
        model.addAttribute("noteList",notesService.getAllNotesByPatientId(patientId));
        model.addAttribute("patientId",patientId);
        return "/NoteList";
    }


}

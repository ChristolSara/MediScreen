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
import webApp.Models.Patient;
import webApp.Service.NotesService;
import webApp.Service.PatientService;

import java.net.URISyntaxException;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Controller
@AllArgsConstructor
public class NoteController {

    private final NotesService notesService;
    private final PatientService patientService;

    @GetMapping("/addNote/{patientId}")
    public String addNote (@PathVariable("patientId")Integer patientId, Model model, Note note){

        model.addAttribute("patientId",patientId);
        return "AddNote";
    }

    @PostMapping("/validateNote")
    public String validate(@Valid Note note, BindingResult result,Model model) throws ParseException {

        if(!result.hasErrors()){
            notesService.addNote(note);
            model.addAttribute("noteList",notesService.getAllNotesByPatientId(note.getPatientId()));
            model.addAttribute("patientId",note.getPatientId());
            return "NoteList";
        }
        return "AddNote";
    }



    @GetMapping("/NoteList/{patientId}")
    public  String getAllNotesByPatientId(@PathVariable("patientId")Integer patientId,Model model){
        model.addAttribute("noteList",notesService.getAllNotesByPatientId(patientId));
        model.addAttribute("patientId",patientId);
        return "NoteList";
    }


    @GetMapping("/deleteNote/{id}")
    public String delete(@PathVariable("id") String id,Model model) throws URISyntaxException {

        Note note= notesService.getNoteById(id);

        notesService.delete(id);
        model.addAttribute("noteList",notesService.getAllNotesByPatientId(note.getPatientId()));

        return "NoteList";
    }


}

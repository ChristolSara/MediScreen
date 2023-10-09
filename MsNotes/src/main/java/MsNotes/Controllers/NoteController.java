package MsNotes.Controllers;

import MsNotes.Models.Note;
import MsNotes.Services.NoteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/addNote")
    public Note addNote(@RequestBody Note note){
        return noteService.addNote(note);
    }


    @GetMapping("/note")
    public Note getNoteById(@RequestParam(name="id")String id){
       return noteService.findNoteById(id);
    }
    @GetMapping("/notesPatient/{patientId}")
    public List<Note> notesPatients(@PathVariable Integer patientId){

        return noteService.findByPatientId(patientId);
    }
    @DeleteMapping("/deleteNote/{patientId}")
    public void deleteNote(@PathVariable Integer patientId){
        noteService.deleteByPatientId(patientId);
    }


}

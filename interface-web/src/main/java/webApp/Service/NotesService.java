package webApp.Service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import webApp.Gateway.NoteGateway;
import webApp.Models.Note;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

@Service @AllArgsConstructor
public class NotesService {

    private final NoteGateway noteGateway;


    public Note addNote(Note note) throws ParseException {

        return noteGateway.addNote(note);

    }

    public List<Note> getAllNotesByPatientId(Integer patientId) {
        return  Arrays.asList(noteGateway.getAllNotesByPatientId(patientId).getBody());

    }
    public Note getNoteById(String id){
        return noteGateway.getNotesById(id).getBody();
    }

    public void delete(String id) {
        noteGateway.delete(id);

    }
}

package webApp.Gateway;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import webApp.Models.Note;

import java.text.ParseException;
@Component
@AllArgsConstructor
public class NoteGateway {


    private final RestTemplate restTemplate;


    public ResponseEntity<Note[]> getAllNotesByPatientId(Integer patientId){
        return restTemplate.getForEntity("http://localhost:8082/notesPatient/{patientId}",Note[].class,patientId);
    }

    public ResponseEntity<Note> getNotesById(String id){
        return restTemplate.getForEntity("http://localhost:8082/note/{id}",Note.class,id);
    }
    public Note addNote(Note note) throws ParseException {

        HttpEntity<Note> request = new HttpEntity<>(note);
        return restTemplate.postForObject("http://localhost:8082/addNote", request, Note.class);

    }
    public void delete(String id) {

        String url = "http://localhost:8082/deleteNote/{id}";
        restTemplate.delete(url, id);

    }

}

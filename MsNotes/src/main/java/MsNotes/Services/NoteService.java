package MsNotes.Services;

import MsNotes.Models.Note;
import MsNotes.Repository.NoteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class NoteService {

    private  final NoteRepository noteRepository;



    public Note findNoteById(String id) {
        return noteRepository.findNoteById(id);
    }

    public Note addNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> findByPatientId(Integer patientId) {
        return noteRepository.findByPatientId(patientId);
    }


    public void deleteByPatientId(Integer patientId) {
        noteRepository.deleteByPatientId(patientId);
    }
}

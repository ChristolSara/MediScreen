package MsNotes.Repository;

import MsNotes.Models.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NoteRepository extends MongoRepository<Note,String> {
    public List<Note> findByPatientId(Integer patientId);
    public Note findNoteById(String id);

    void deleteByPatientId(Integer patientId);


}

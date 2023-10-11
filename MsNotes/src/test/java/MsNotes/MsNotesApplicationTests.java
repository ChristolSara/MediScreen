package MsNotes;

import MsNotes.Controllers.NoteController;
import MsNotes.Models.Note;
import MsNotes.Repository.NoteRepository;
import org.apache.tomcat.util.json.ParseException;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@SpringBootTest
class MsNotesApplicationTests {

	@Autowired
	NoteController noteController;

	@Test
	void contextLoads() {
	}

	@Test
	public void main() {

	}

	@Test
	public void saveNote() {
		Note note = new Note("null", 1, "debut de concer", new Date());
		noteController.addNote(note);


		Assert.assertEquals(note.getNote(), "debut de concer");
		noteController.deleteNote(note.getId());

	}

	@Test
	public void testFindNoteById() {
		Note note = new Note("null", 1, "debut de concer", new Date());
		noteController.addNote(note);
		Note note1 = noteController.getNoteById(note.getId());
		Assert.assertEquals(note1.getNote(), "debut de concer");
		noteController.deleteNote(note.getId());

	}

	@Test
	public void testFindAllNotes() {
		List<Note> notes = noteController.notesPatients(1);
		Assert.assertTrue(notes.size() >= 0);
	}

	@Test
	public void deleteNote() throws ParseException {
		Note note = new Note("null", 1, "debut de concer", new Date());
		noteController.addNote(note);
		Note note1 = noteController.getNoteById(note.getId());
		noteController.deleteNote(note1.getId());
	}






}

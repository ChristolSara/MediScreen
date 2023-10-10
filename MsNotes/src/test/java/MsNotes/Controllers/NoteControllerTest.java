package MsNotes.Controllers;

import MsNotes.Models.Note;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class NoteControllerTest {


    @Autowired
    private NoteController noteController;

    @Test
    void addNote() {
        Note note= new Note(null,4,"test note",new Date());
        noteController.addNote(note);

       List<Note> noteList= noteController.notesPatients(4);
        Assert.assertEquals(true,noteList.contains(note));

    }

    @Test
    void getNoteById() {

        Note note= new Note(null,5,"test note get by id",new Date());
        noteController.addNote(note);

        Note noteTest = noteController.getNoteById(note.getId());
        Assert.assertEquals(true,noteController.notesPatients(5).contains(note));
        Assert.assertTrue(noteTest.getNote().equals("test note get by id"));


    }

    @Test
    void notesPatients() {
        Note note= new Note(null,15,"test note get notes",new Date());
        noteController.addNote(note);

        List<Note> noteList= noteController.notesPatients(15);
        Assert.assertEquals(true,noteList.contains(note));

        Assert.assertTrue(noteList.size()>=0);


    }

    @Test
    void deleteNote() {

        Note note= new Note(null,150,"test note delete1",new Date());
        Note note1= new Note(null,150,"test note delete2",new Date());
        Note note3= new Note(null,150,"test note delete3",new Date());


        noteController.addNote(note);
        noteController.addNote(note1);
        noteController.addNote(note3);

        List<Note> noteList=noteController.notesPatients(150);

        noteController.deleteNote(note1.getId());
        List<Note> notes = noteController.notesPatients(150);


        Assert.assertEquals(true,noteList.contains(note1));

        Assert.assertTrue(noteList.size()>=2);
        Assert.assertEquals(false,notes.contains(note1));






    }
}
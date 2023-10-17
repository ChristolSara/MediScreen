package msDiabete.Service;

import lombok.AllArgsConstructor;
import msDiabete.Gateway.NoteGateway;
import msDiabete.Gateway.PatientGateway;
import msDiabete.Models.Note;
import msDiabete.Models.Patient;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class DiabeteService {
    private final PatientGateway patientGateway;
    private final NoteGateway noteGateway;

    public Integer computeAge(Patient patient) {

        Date curDate = new Date();
        Date birthday = patient.getBirthday();

        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");

        int d1 = Integer.parseInt(formatter.format(curDate));
        int d2 = Integer.parseInt(formatter.format(birthday));
        int age = (d1 - d2) / 10000;


        return age;
    }

    public Integer computeDeclench(Patient patient) {

        int nbr = 1;

        List<Note> noteList = List.of(noteGateway.getAllNotesByPatientId(patient.getId()).getBody());
        List<String> notes = noteList.stream().map(Note::getNote).collect(Collectors.toList());

        String[] declenchers = new String[]{
                "Hémoglobine A1C",
                "Microalbumine",
                "Taille",
                "Poids",
                "Fumeur",
                "Anormal",
                "Cholestérol",
                "Vertige",
                "Rechute",
                "Réaction",
                "Anticorps"};


        String dec = null;


        for (int j = 0; j < declenchers.length ; j++) {

            dec = declenchers[j];

            if (notes.contains(dec)) {

                nbr++;

            }
        }
        return nbr;
    }

    public String diabeteAssess(Patient patient) {

        int age = computeAge(patient);
        int nbrDec = computeDeclench(patient);
        String gendre = patient.getGenre().toString();

        String response = "";

        //age < 30

        if (age <= 30 && nbrDec == 0) {
            response = "None";
        } else if (age <= 30 && ( nbrDec <= 5) && (gendre == "HOMME")) {
            response += "Early onset";
        } else if (age <= 30 && (nbrDec <= 7) && (gendre == "FEMME")) {
            response += "Early onset";
        }



        else if (age > 30 && nbrDec == 0) {
            response += "Borderline";
        }else if (age > 30 && nbrDec <= 2) {
            response += "Borderline";
        } else if (age <= 30 && (nbrDec <= 3) && (gendre == "HOMME")) {
            response += "In Danger";
        } else if (age <= 30 && (nbrDec <= 4) && (gendre == "FEMME")) {
            response += "In Danger";
        } else if ((age > 30) && (nbrDec <= 6)) {
            response += "In Danger";
        }  else if ((age > 30) && (nbrDec >= 8)) {
            response += "Early onset";
        }else{
            return "no response";
        }

        return response;
    }


}

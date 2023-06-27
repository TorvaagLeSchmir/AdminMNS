package com.projet.filrouge.Contrôleurs;

import com.projet.filrouge.DAO.PersonneDAOImpl;
import com.projet.filrouge.Modèles.Personne;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class PersonneController {

    private PersonneDAOImpl personneDAO;

    public PersonneController(PersonneDAOImpl personneDAO) {
        this.personneDAO = personneDAO;
    }

    @GetMapping("/admin/finduser")
    public ResponseEntity<Personne> testFindByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        Optional<Personne> personneOpt = Optional.ofNullable(personneDAO.findByNomAndPrenom(nom, prenom));
        if (personneOpt.isPresent()) {
            return new ResponseEntity<>(personneOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/admin/stagiaires")
    public ResponseEntity<List<Personne>> getStagiaires() {
        List<Personne> stagiaires = personneDAO.findAllStagiaires();
        if (!stagiaires.isEmpty()) {
            return new ResponseEntity<>(stagiaires, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/findemail")
    public ResponseEntity<Personne> findemail(@RequestParam String emailMNS) {
        Optional<Personne> personneOpt = personneDAO.findByEmail(emailMNS);

        if (personneOpt.isPresent()) {
            return new ResponseEntity<>(personneOpt.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
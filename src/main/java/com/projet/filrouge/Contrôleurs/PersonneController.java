package com.projet.filrouge.Contrôleurs;

import com.projet.filrouge.DAO.PersonneDAOImpl;
import com.projet.filrouge.Modèles.Personne;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin
public class PersonneController {

    private PersonneDAOImpl personneDAO;

    public PersonneController(PersonneDAOImpl personneDAO) {
        this.personneDAO = personneDAO;
    }

    @GetMapping("/admin/finduser")
    public void testFindByNomAndPrenom(@RequestParam String nom, @RequestParam String prénom) {
        Personne personne = personneDAO.findByNomAndPrenom(nom, prénom);
        if (personne != null) {
            System.out.println("Personne trouvée : " + personne.getNom() + " " + personne.getPrénom());
        } else {
            System.out.println("Aucune personne trouvée avec le nom et prénom spécifiés.");
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
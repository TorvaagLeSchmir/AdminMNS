package com.projet.filrouge.Contrôleurs;

import com.projet.filrouge.DAO.PersonneDAOImpl;
import com.projet.filrouge.Modèles.Personne;
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
    public void testFindByEmail(@RequestParam String emailMNS) {
        Optional<Personne> personneOptionnel = personneDAO.findByEmail(emailMNS);
        personneOptionnel.ifPresent(personne -> System.out.println("Personne trouvée : " + personne.getNom() + " " + personne.getPrénom()));
        if (personneOptionnel.isEmpty()) {
            System.out.println("Aucune personne trouvée avec l'email spécifié.");
        }
    }
}
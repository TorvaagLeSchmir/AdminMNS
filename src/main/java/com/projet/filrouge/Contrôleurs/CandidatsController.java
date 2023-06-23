package com.projet.filrouge.Contrôleurs;

import com.projet.filrouge.DAO.InscriptionDataDAOImpl;
import com.projet.filrouge.Modèles.InscriptionData;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class CandidatsController {

    private final InscriptionDataDAOImpl inscriptionDataDAO;

    public CandidatsController(InscriptionDataDAOImpl inscriptionDataDAO) {
        this.inscriptionDataDAO = inscriptionDataDAO;
    }


    @GetMapping("/admin/profil/candidats")
    public List<InscriptionData> getCandidats(){
    return inscriptionDataDAO.getCandidats();
    }

}

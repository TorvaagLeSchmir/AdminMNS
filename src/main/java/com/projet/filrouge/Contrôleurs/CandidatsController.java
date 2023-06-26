package com.projet.filrouge.Contrôleurs;

import com.projet.filrouge.DAO.InscriptionDataDAOImpl;
import com.projet.filrouge.DAO.PersonneDAOImpl;
import com.projet.filrouge.Modèles.Infos;
import com.projet.filrouge.Modèles.Personne;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class CandidatsController {

    private final InscriptionDataDAOImpl inscriptionDataDAO;
    private final PersonneDAOImpl personneDAO;

    public CandidatsController(InscriptionDataDAOImpl inscriptionDataDAO, PersonneDAOImpl personneDAO) {
        this.inscriptionDataDAO = inscriptionDataDAO;
        this.personneDAO = personneDAO;
    }


    @GetMapping("/admin/profil/candidats")
    public List<Map<String, Object>> getCandidats(){
        Map<Personne, Infos> candidatsMap = inscriptionDataDAO.getCandidats();

        // Convertir la Map en List
        List<Map<String, Object>> result = new ArrayList<>();
        for (Map.Entry<Personne, Infos> entry : candidatsMap.entrySet()) {
            Map<String, Object> item = new HashMap<>();
            item.put("personne", entry.getKey());
            item.put("infos", entry.getValue());
            result.add(item);
        }

        return result;
    }


    @PutMapping("/admin/accepter-candidature/{id}")
    public void accepterCandidature(@PathVariable int id) throws SQLException {
        personneDAO.accepterCandidature(id);
    }

    @PutMapping("/admin/refuser-candidature/{id}")
    public void refuserCandidature(@PathVariable int id){
        personneDAO.refuserCandidature(id);
    }

}

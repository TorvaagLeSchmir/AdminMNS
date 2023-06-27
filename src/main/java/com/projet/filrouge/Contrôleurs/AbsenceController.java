package com.projet.filrouge.Contrôleurs;

import com.projet.filrouge.DAO.AbsenceDAOimpl;
import com.projet.filrouge.Modèles.Absence;
import com.projet.filrouge.Modèles.Personne;
import com.projet.filrouge.Services.FileStorageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class AbsenceController {

    private final FileStorageService fileStorageService;
    private final AbsenceDAOimpl absenceDAO;


    public AbsenceController(FileStorageService fileStorageService, AbsenceDAOimpl absenceDAO) {
        this.fileStorageService = fileStorageService;
        this.absenceDAO = absenceDAO;

    }

    @PostMapping("/absence")
    public ResponseEntity<String> déclarerAbsence(
            @RequestParam(value = "debutAbsence", required = false) String debutAbsence,
            @RequestParam("finAbsence") String finAbsence,
            @RequestParam("motif") String motif,
            @RequestParam(value = "justificatif", required = false) MultipartFile justificatif) {

        try {

            Absence absence = new Absence();

            absence.setDateFin(LocalDateTime.parse(finAbsence));
            absence.setDateDéclaration(new Date(System.currentTimeMillis()));
            absence.setMotif(motif);
            if (justificatif != null) {
                String justificatifTxt = fileStorageService.storeFileJustificatif(justificatif);
                absence.setJustificatifTxt(justificatifTxt);
            }
            if (debutAbsence != null) {
                absence.setDateDébut(LocalDateTime.parse(debutAbsence));
                absenceDAO.nouvelleAbsence(absence);
            } else {

                absenceDAO.nouveauRetard(absence);
            }

            return ResponseEntity.status(HttpStatus.CREATED).body("Déclaration effectuée avec succès");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la déclaration");
        }
    }

    @GetMapping("/admin/retard")
    public List<Absence> getRetardsAValider() {
        return absenceDAO.retardsAValider();
    }

    @GetMapping("/admin/absence")
    public List<Absence> getAbsencesAValider(){return absenceDAO.absencesAValider();}

    @GetMapping("/admin/absencePersonne")
    public List<Personne> getAbsencePersonne() {
        return absenceDAO.getAbsencePersonne();
    }
    @GetMapping("/admin/retardPersonne")
    public List<Personne> getRetardPersonne(){
        return absenceDAO.getRetardsPersonne();
    }

    @PutMapping("/admin/absence/{id}")
    public void justifiedOrNot(@PathVariable("id") int id, @RequestBody Map<String, Object> payload) {

        boolean isJustified = (boolean) payload.get("isJustified");
        absenceDAO.updateJustification(id, isJustified);
        }
    @GetMapping("/admin/stagiaires/{id}/absences")
    public List<Absence> getAbsencesParStagiaire(@PathVariable int id) {
        return absenceDAO.getAbsencesParPersonne(id);
    }
    @GetMapping("/admin/stagiaires/{id}/retards")
    public List<Absence> getRetardsParStagiaire(@PathVariable int id) {
        return absenceDAO.getRetardsParPersonne(id);
    }
    }

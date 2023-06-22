package com.projet.filrouge.Contrôleurs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.filrouge.DAO.PersonneDAOImpl;
import com.projet.filrouge.Modèles.Infos;
import com.projet.filrouge.Modèles.InscriptionData;
import com.projet.filrouge.Modèles.Personne;
import com.projet.filrouge.Modèles.Rôle;
import com.projet.filrouge.Services.FileStorageService;
import com.projet.filrouge.Sécurité.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class InscriptionController {
    private
    PersonneDAOImpl personneDAO;
    private FileStorageService fileStorageService;
    private
    PasswordEncoder passwordEncoder;
    private
    AuthenticationManager authenticationManager;
    private
    JwtUtils jwtUtils;

    public InscriptionController(PersonneDAOImpl personneDAO, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JwtUtils jwtUtils, FileStorageService fileStorageService) {
        this.personneDAO = personneDAO;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.fileStorageService = fileStorageService;
    }

    @PostMapping(value = "/inscription", consumes = {"multipart/form-data"})
    public ResponseEntity<Personne> inscription(@RequestParam("inscriptionData") String inscriptionDataStr,
                                                @RequestParam("photo") MultipartFile photo,
                                                @RequestParam("copie_id") MultipartFile copieId,
                                                @RequestParam("cv") MultipartFile cv,
                                                @RequestParam("copie_diplome") MultipartFile copieDiplome,
                                                @RequestParam("bulletins_notes") MultipartFile bulletinsNotes,
                                                @RequestParam("lettre_motivation") MultipartFile lettreMotivation) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        InscriptionData inscriptionData = objectMapper.readValue(inscriptionDataStr, InscriptionData.class);
        inscriptionData.setPhoto(photo);
        inscriptionData.setCopie_id(copieId);
        inscriptionData.setCv(cv);
        inscriptionData.setCopie_diplome(copieDiplome);
        inscriptionData.setBulletins_notes(bulletinsNotes);
        inscriptionData.setLettre_motivation(lettreMotivation);

    Personne personne = new Personne(inscriptionData.getNom(), inscriptionData.getPrenom());
        boolean check = (personneDAO.checkByNomAndPrenom(personne.getNom(), personne.getPrénom()));

        if (!check) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        personne.setTel(inscriptionData.getTelephone());
        personne.setDateNaissance(inscriptionData.getDate_de_naissance());
        personne.setRue(inscriptionData.getAdresse());
        personne.setCodePostal(inscriptionData.getCode_postal());
        personne.setVille(inscriptionData.getVille());
        personne.setNationalité(inscriptionData.getNationalite());
        personne.setEmailPerso(inscriptionData.getEmail());

        Infos infos = new Infos(
                inscriptionData.getDiplome(),
                inscriptionData.getFormation_type(),
                inscriptionData.getAmenagements(),
                inscriptionData.getAmenagementsInfo(),
                inscriptionData.getTiers_temps(),
                inscriptionData.getLangages(),
                inscriptionData.getProjets_url(),
                inscriptionData.getFormation_interesse(),
                inscriptionData.getChoix_mns(),
                inscriptionData.getChoix_formation(),
                inscriptionData.getProjet_pro(),
                inscriptionData.getSecteur_stage(),
                inscriptionData.getQualites(),
                inscriptionData.getDefauts(),
                inscriptionData.getPassions(),
                inscriptionData.getNiveau_anglais(),
                inscriptionData.getAutre_langue(),
                inscriptionData.getAutre_langueInfo(),
                inscriptionData.getPermis_b(),
                inscriptionData.getVehicule(),
                inscriptionData.getMobilite(),
                inscriptionData.getExpatriation_stage(),
                inscriptionData.getConnu_mns(),
                inscriptionData.isAcceptation_politique(),
                inscriptionData.getAncien_stagiaire_prenom(),
                inscriptionData.getAncien_stagiaire_nom(),
                inscriptionData.getAncien_stagiaire_formation()
        );



        infos.setPhoto(fileStorageService.storeFileDocument(inscriptionData.getPhoto()));
        infos.setCopie_id(fileStorageService.storeFileDocument(inscriptionData.getCopie_id()));
        infos.setCv(fileStorageService.storeFileDocument(inscriptionData.getCv()));
        infos.setCopie_diplome(fileStorageService.storeFileDocument(inscriptionData.getCopie_diplome()));
        infos.setBulletins_notes(fileStorageService.storeFileDocument(inscriptionData.getBulletins_notes()));
        infos.setLettre_motivation(fileStorageService.storeFileDocument(inscriptionData.getLettre_motivation()));

        ObjectMapper mapper = new ObjectMapper();

        String jsonString = mapper.writeValueAsString(infos);
        String uniqueFileName = UUID.randomUUID().toString() + "_infos.json";
        String targetPath = "/Users/BlackCalx/Desktop/DEV/AdminMNS_inscriptionData/" + uniqueFileName;

        try (FileWriter fileWriter = new FileWriter(targetPath))
        {
            fileWriter.write(jsonString);
        }
        personne.setInfos(targetPath);

//        String passwordHache = passwordEncoder.encode(personne.getMotDePasse());
//        personne.setMotDePasse(passwordHache);
        personne.setListeRoles(new ArrayList<Rôle>(){
            {
                add(new Rôle(3));
            }
            });
        personneDAO.inscription(personne);
        return new ResponseEntity<>(personne, HttpStatus.CREATED);
    }
    }
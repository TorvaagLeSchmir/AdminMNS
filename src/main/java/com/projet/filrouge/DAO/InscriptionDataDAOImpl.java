package com.projet.filrouge.DAO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.filrouge.Modèles.InscriptionData;
import com.projet.filrouge.Services.FileService;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InscriptionDataDAOImpl implements InscriptionDataDAO {

    private final FileService fileService;

    public InscriptionDataDAOImpl(FileService fileService) {
        this.fileService = fileService;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/AdminMNS", "root","");
    }

        @Override
        public List<InscriptionData> getCandidats(){
            String query = "SELECT * FROM Personne JOIN Personnerole ON Personne.id_p = Personnerole.id_personne WHERE Personnerole.id_role = ?";
            List<InscriptionData> candidats = new ArrayList<>();
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, 3);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    InscriptionData inscriptionData = new InscriptionData();
                    inscriptionData.setTelephone(resultSet.getString("tel_p"));
                    inscriptionData.setDate_de_naissance(resultSet.getDate("dateNaissance_p"));
                    inscriptionData.setNom(resultSet.getString("nom_p"));
                    inscriptionData.setPrenom(resultSet.getString("prenom_p"));
                    inscriptionData.setVille(resultSet.getString("ville_p"));
                    inscriptionData.setAdresse(resultSet.getString("rue_p"));
                    inscriptionData.setCode_postal(resultSet.getString("CP_p"));
                    inscriptionData.setNationalite(resultSet.getString("nationalite_p"));
                    inscriptionData.setEmail(resultSet.getString("emailPerso_p"));
                    String infosString = resultSet.getString("infos_p");
                    if (infosString != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
                        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);

                        String jsonContent = new String(Files.readAllBytes(Paths.get(infosString)));
                        JsonNode infosJson = mapper.readTree(jsonContent);

                        inscriptionData.setDiplome(infosJson.get("diplome").asText());
                        inscriptionData.setFormation_type(infosJson.get("formation_type").asText());
                        inscriptionData.setAmenagements(infosJson.get("amenagements").asText());
                        inscriptionData.setAmenagementsInfo(infosJson.get("amenagementsInfo").asText());
                        inscriptionData.setTiers_temps(infosJson.get("tiers_temps").asText());
                        inscriptionData.setLangages(infosJson.get("langages").asText());
                        inscriptionData.setProjets_url(infosJson.get("projets_url").asText());
                        inscriptionData.setFormation_interesse(infosJson.get("formation_interesse").asText());
                        inscriptionData.setChoix_mns(infosJson.get("choix_mns").asText());
                        inscriptionData.setChoix_formation(infosJson.get("choix_formation").asText());
                        inscriptionData.setProjet_pro(infosJson.get("projet_pro").asText());
                        inscriptionData.setSecteur_stage(infosJson.get("secteur_stage").asText());
                        inscriptionData.setQualites(infosJson.get("qualites").asText());
                        inscriptionData.setDefauts(infosJson.get("defauts").asText());
                        inscriptionData.setPassions(infosJson.get("passions").asText());
                        inscriptionData.setNiveau_anglais(infosJson.get("niveau_anglais").asText());
                        inscriptionData.setAutre_langue(infosJson.get("autre_langue").asText());
                        inscriptionData.setAutre_langueInfo(infosJson.get("autre_langueInfo").asText());
                        inscriptionData.setPermis_b(infosJson.get("permis_b").asText());
                        inscriptionData.setVehicule(infosJson.get("vehicule").asText());
                        inscriptionData.setMobilite(infosJson.get("mobilite").asText());
                        inscriptionData.setExpatriation_stage(infosJson.get("expatriation_stage").asText());
                        inscriptionData.setConnu_mns(infosJson.get("connu_mns").asText());
                        inscriptionData.setAcceptation_politique(infosJson.get("acceptation_politique").asBoolean());
                        inscriptionData.setAncien_stagiaire_prenom(infosJson.get("ancien_stagiaire_prenom").asText());
                        inscriptionData.setAncien_stagiaire_nom(infosJson.get("ancien_stagiaire_nom").asText());
                        inscriptionData.setAncien_stagiaire_formation(infosJson.get("ancien_stagiaire_formation").asText());

                        inscriptionData.setPhoto(fileService.fetchFile(infosJson.get("photo").asText()));
                        inscriptionData.setCopie_id(fileService.fetchFile(infosJson.get("copie_id").asText()));
                        inscriptionData.setCv(fileService.fetchFile(infosJson.get("cv").asText()));
                        inscriptionData.setCopie_diplome(fileService.fetchFile(infosJson.get("copie_diplome").asText()));
                        inscriptionData.setBulletins_notes(fileService.fetchFile(infosJson.get("bulletins_notes").asText()));
                        inscriptionData.setLettre_motivation(fileService.fetchFile(infosJson.get("lettre_motivation").asText()));

                    }
                    candidats.add(inscriptionData);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            return candidats;
        }
}


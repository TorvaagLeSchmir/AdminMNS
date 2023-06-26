package com.projet.filrouge.DAO;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projet.filrouge.Modèles.Infos;
import com.projet.filrouge.Modèles.Personne;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

@Repository
public class InscriptionDataDAOImpl implements InscriptionDataDAO {


    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/AdminMNS", "root","");
    }

        @Override
        public Map<Personne, Infos> getCandidats(){
            String query = "SELECT * FROM Personne JOIN Personnerole ON Personne.id_p = Personnerole.id_personne WHERE Personnerole.id_role = ?";
            Map<Personne, Infos> candidats = new HashMap<>() {
            };
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)){
                statement.setInt(1, 3);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Personne personne = new Personne();
                    Infos infos = new Infos();

                    personne.setTel(resultSet.getString("tel_p"));
                    personne.setDateNaissance(resultSet.getDate("dateNaissance_p"));
                    personne.setNom(resultSet.getString("nom_p"));
                    personne.setPrénom(resultSet.getString("prenom_p"));
                    personne.setVille(resultSet.getString("ville_p"));
                    personne.setRue(resultSet.getString("rue_p"));
                    personne.setCodePostal(resultSet.getString("CP_p"));
                    personne.setNationalité(resultSet.getString("nationalite_p"));
                    personne.setEmailPerso(resultSet.getString("emailPerso_p"));
                    String infosString = resultSet.getString("infos_p");
                    if (infosString != null) {
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
                        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);


                        String jsonContent = new String(Files.readAllBytes(Paths.get(infosString)));
                        JsonNode infosJson = mapper.readTree(jsonContent);

                        infos.setDiplome(infosJson.get("diplome").asText());
                        infos.setFormation_type(infosJson.get("formation_type").asText());
                        infos.setAmenagements(infosJson.get("amenagements").asText());
                        infos.setAmenagementsInfo(infosJson.get("amenagementsInfo").asText());
                        infos.setTiers_temps(infosJson.get("tiers_temps").asText());
                        infos.setLangages(infosJson.get("langages").asText());
                        infos.setProjets_url(infosJson.get("projets_url").asText());
                        infos.setFormation_interesse(infosJson.get("formation_interesse").asText());
                        infos.setChoix_mns(infosJson.get("choix_mns").asText());
                        infos.setChoix_formation(infosJson.get("choix_formation").asText());
                        infos.setProjet_pro(infosJson.get("projet_pro").asText());
                        infos.setSecteur_stage(infosJson.get("secteur_stage").asText());
                        infos.setQualites(infosJson.get("qualites").asText());
                        infos.setDefauts(infosJson.get("defauts").asText());
                        infos.setPassions(infosJson.get("passions").asText());
                        infos.setNiveau_anglais(infosJson.get("niveau_anglais").asText());
                        infos.setAutre_langue(infosJson.get("autre_langue").asText());
                        infos.setAutre_langueInfo(infosJson.get("autre_langueInfo").asText());
                        infos.setPermis_b(infosJson.get("permis_b").asText());
                        infos.setVehicule(infosJson.get("vehicule").asText());
                        infos.setMobilite(infosJson.get("mobilite").asText());
                        infos.setExpatriation_stage(infosJson.get("expatriation_stage").asText());
                        infos.setConnu_mns(infosJson.get("connu_mns").asText());
                        infos.setAcceptation_politique(infosJson.get("acceptation_politique").asBoolean());
                        infos.setAncien_stagiaire_prenom(infosJson.get("ancien_stagiaire_prenom").asText());
                        infos.setAncien_stagiaire_nom(infosJson.get("ancien_stagiaire_nom").asText());
                        infos.setAncien_stagiaire_formation(infosJson.get("ancien_stagiaire_formation").asText());

                        infos.setPhoto(infosJson.get("photo").asText());
                        infos.setCopie_id(infosJson.get("copie_id").asText());
                        infos.setCv(infosJson.get("cv").asText());
                        infos.setCopie_diplome(infosJson.get("copie_diplome").asText());
                        infos.setBulletins_notes(infosJson.get("bulletins_notes").asText());
                        infos.setLettre_motivation(infosJson.get("lettre_motivation").asText());

                    }
                    candidats.put(personne, infos);
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
            return candidats;
        }
}


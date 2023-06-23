package com.projet.filrouge.DAO;

import com.projet.filrouge.Modèles.Personne;
import com.projet.filrouge.Modèles.Rôle;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PersonneDAOImpl implements PersonneDAO {
    public Connection getConnection() throws SQLException {
       return DriverManager.getConnection("jdbc:mysql://localhost:3306/adminmns", "root","");
    }
    @Override
    public int getIdByUsername(String username) {
        String query = "SELECT id_p FROM Personne WHERE emailMNS_p = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_P");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    @Override
    public int getLoggedInUserId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();

            return this.getIdByUsername(username);
        }
        return -1;
    }


    @Override
    public Personne findByNomAndPrenom(String nom, String prenom) {
        String query = "SELECT personne.*, personnerole.id_role FROM Personne LEFT JOIN personnerole ON personne.id_p = personnerole.id_personne WHERE nom_p = ? AND prenom_p = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            statement.setString(2, prenom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToPersonne(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public boolean checkByNomAndPrenom(String nom, String prenom) {
        String query = "SELECT emailMNS_p FROM Personne WHERE nom_p = ? AND prenom_p = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nom);
            statement.setString(2, prenom);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    @Override
    public Personne mapResultSetToPersonne(ResultSet resultSet) throws SQLException {
        Personne personne = new Personne();
        personne.setId(resultSet.getInt("id_p"));
        personne.setNom(resultSet.getString("nom_p"));
        personne.setPrénom(resultSet.getString("prenom_p"));
        personne.setEmailMNS(resultSet.getString("emailMNS_p"));
        personne.setMotDePasse(resultSet.getString("mdp_p"));

        List<Rôle> rôles = getRoleDetails(personne.getId());
        if (rôles != null) {
            personne.setListeRoles(rôles);
        }
        return personne;
    }




    @Override
    public Optional<Personne> findByEmail(String emailMNS) {
        String query = "SELECT personne.*, personnerole.id_role FROM personne LEFT JOIN personnerole ON personne.id_p = personnerole.id_personne WHERE emailMNS_p = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, emailMNS);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapResultSetToPersonne(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public void inscription(Personne personne) {
        String query = "INSERT INTO Personne (nom_p, prenom_p, tel_p, dateNaissance_p, rue_p, CP_p, ville_p, emailPerso_p, nationalite_p, infos_p) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, personne.getNom());
            statement.setString(2, personne.getPrénom());
            statement.setString(3, personne.getTel());
            statement.setDate(4, personne.getDateNaissance());
            statement.setString(5, personne.getRue());
            statement.setString(6, personne.getCodePostal());
            statement.setString(7, personne.getVille());
            statement.setString(8, personne.getEmailPerso());
            statement.setString(9, personne.getNationalité());
            statement.setString(10, personne.getInfos());


            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int personneId = generatedKeys.getInt(1);
                insererRole(personneId, personne.getListeRoles());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void insererRole(int personneId, List<Rôle> roles) {
        String query = "INSERT INTO personnerole (id_personne, id_role) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            for (Rôle role : roles) {
                statement.setInt(1, personneId);
                statement.setInt(2, role.getId_statut());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public List<Rôle> getRoleDetails(int personneId) {
        String query = "SELECT role.* FROM role JOIN personnerole ON role.id_r = personnerole.id_role WHERE personnerole.id_personne = ?";
        List<Rôle> rôles = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, personneId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Rôle rôle = new Rôle();
                rôle.setId_statut(resultSet.getInt("id_r"));
                rôle.setNom(resultSet.getString("nom_r"));
                rôles.add(rôle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rôles;
    }

    }
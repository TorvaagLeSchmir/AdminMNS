package com.projet.filrouge.DAO;

import com.projet.filrouge.Modèles.Personne;
import com.projet.filrouge.Modèles.Rôle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface PersonneDAO {

    Connection getConnection() throws SQLException;
    int getIdByUsername(String username);
    int getLoggedInUserId();
    Personne findByNomAndPrenom(String nom, String prénom);
    boolean checkByNomAndPrenom(String nom, String prenom);
    Personne mapResultSetToPersonne(ResultSet resultSet) throws SQLException;
    void insererRole(int personneId, List<Rôle> roles);
    List<Rôle> getRoleDetails(int personneId);
    Optional<Personne> findByEmail(String emailMNS);
    void inscription(Personne personne);

    }
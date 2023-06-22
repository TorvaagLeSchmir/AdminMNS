package com.projet.filrouge.DAO;

import com.projet.filrouge.Modèles.Personne;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public interface PersonneDAO {

    Connection getConnection() throws SQLException;
    Personne findByNomAndPrenom(String nom, String prénom);

    Optional<Personne> findByEmail(String emailMNS);

    void inscription(Personne personne);
}
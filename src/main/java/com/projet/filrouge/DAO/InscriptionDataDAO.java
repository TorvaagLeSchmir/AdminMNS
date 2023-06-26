package com.projet.filrouge.DAO;

import com.projet.filrouge.Modèles.Infos;
import com.projet.filrouge.Modèles.Personne;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

public interface InscriptionDataDAO {

    Connection getConnection() throws SQLException;

    Map<Personne, Infos> getCandidats();


}

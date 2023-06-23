package com.projet.filrouge.DAO;

import com.projet.filrouge.Modèles.InscriptionData;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface InscriptionDataDAO {

    Connection getConnection() throws SQLException;

    List<InscriptionData> getCandidats();


}

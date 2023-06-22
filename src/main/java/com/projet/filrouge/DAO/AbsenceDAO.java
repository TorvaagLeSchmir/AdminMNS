package com.projet.filrouge.DAO;

import com.projet.filrouge.Modèles.Absence;

import java.sql.Connection;
import java.sql.SQLException;

public interface AbsenceDAO {
     Connection getConnection() throws SQLException;

     Absence findByID(int id);

     void nouvelleAbsence(Absence absence);
     void nouveauRetard(Absence absence);

}

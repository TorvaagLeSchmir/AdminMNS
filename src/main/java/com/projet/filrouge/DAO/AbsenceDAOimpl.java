package com.projet.filrouge.DAO;

import com.projet.filrouge.Modèles.Absence;
import com.projet.filrouge.Modèles.Personne;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AbsenceDAOimpl implements AbsenceDAO {

    PersonneDAOImpl personneDAO = new PersonneDAOImpl();

    public AbsenceDAOimpl(PersonneDAOImpl personneDAO) {
        this.personneDAO = personneDAO;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/AdminMNS", "root","");
    }

    public Absence findByID(int id) {
        String query = "SELECT * FROM Absence WHERE id_m = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToAbsence(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private Absence mapResultSetToAbsence(ResultSet resultSet) throws SQLException {
        Absence absence = new Absence();
        absence.setId(resultSet.getInt("id_m"));
        Timestamp dateDebutTimestamp = resultSet.getTimestamp("dateDebut_m");
        if (dateDebutTimestamp != null) {
            absence.setDateDébut(dateDebutTimestamp.toLocalDateTime());
        }
        absence.setDateFin(resultSet.getTimestamp("dateFin_m").toLocalDateTime());
        absence.setDateDéclaration(resultSet.getDate("dateDeclaration_m"));
        absence.setMotif(resultSet.getString("motif_m"));
        absence.setJustificatifTxt(resultSet.getString("justificatiftxt_m"));
        absence.setRetard(resultSet.getBoolean("isRetard"));
        absence.setChecked(resultSet.getBoolean("isChecked"));
        absence.setJustified(resultSet.getBoolean("isJustified"));

        return absence;
    }

    public void nouvelleAbsence(Absence absence) {
        String query = "INSERT INTO Absence (dateDebut_m, dateFin_m, dateDeclaration_m, motif_m, justificatifTxt_m, isRetard, isChecked) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, Timestamp.valueOf(absence.getDateDébut()));
            statement.setTimestamp(2, Timestamp.valueOf(absence.getDateFin()));
            statement.setDate(3, absence.getDateDéclaration());
            statement.setString(4, absence.getMotif());
            statement.setString(5, absence.getJustificatifTxt());
            statement.setBoolean(6, false);
            statement.setBoolean(7, false);

            statement.executeUpdate();


            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int absenceId = generatedKeys.getInt(1);

                    String sqlJoin = "INSERT INTO PersonneAbsence (id_personne, id_absence) VALUES (?, ?)";
                    PreparedStatement statementJoin = connection.prepareStatement(sqlJoin);

                    statementJoin.setInt(1, personneDAO.getLoggedInUserId());
                    statementJoin.setInt(2, absenceId);
                    statementJoin.executeUpdate();
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void nouveauRetard(Absence absence) {
        String query = "INSERT INTO Absence (dateFin_m, dateDeclaration_m, motif_m, justificatifTxt_m, isRetard, isChecked) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setTimestamp(1, Timestamp.valueOf(absence.getDateFin()));
            statement.setDate(2, absence.getDateDéclaration());
            statement.setString(3, absence.getMotif());
            statement.setString(4, absence.getJustificatifTxt());
            statement.setBoolean(5, true);
            statement.setBoolean(6, false);
            statement.executeUpdate();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int absenceId = generatedKeys.getInt(1);

                    String sqlJoin = "INSERT INTO PersonneAbsence (id_personne, id_absence) VALUES (?, ?)";
                    PreparedStatement statementJoin = connection.prepareStatement(sqlJoin);

                    statementJoin.setInt(1, personneDAO.getLoggedInUserId());
                    statementJoin.setInt(2, absenceId);
                    statementJoin.executeUpdate();
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Absence> absencesAValider() {
        String query = "Select * FROM Absence where isRetard = ? and isChecked = ?";
        List<Absence> absences = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, false);
            statement.setBoolean(2, false);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Absence absence = mapResultSetToAbsence(resultSet);
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }
    public List<Absence> retardsAValider() {
        String query = "Select * FROM Absence where isRetard = ? and isChecked = ?";
        List<Absence> absences = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            statement.setBoolean(2, false);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Absence absence = mapResultSetToAbsence(resultSet);
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }
    public List<Personne> getRetardsPersonne() {
        List<Absence> absences = retardsAValider();
        List<Personne> personnes = new ArrayList<>();
        for (Absence absence : absences) {
            String query = "SELECT p.* FROM Personne p JOIN PersonneAbsence pa ON p.id_p = pa.id_personne JOIN Absence a ON pa.id_absence = a.id_m WHERE a.isRetard = ? AND a.id_m = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(2, absence.getId());
                statement.setBoolean(1,true);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Personne personne = personneDAO.mapResultSetToPersonne(resultSet);
                    personnes.add(personne);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personnes;
    }
    public List<Personne> getAbsencePersonne() {
        List<Absence> absences = absencesAValider();
        List<Personne> personnes = new ArrayList<>();
        for (Absence absence : absences) {
            String query = "SELECT p.* FROM Personne p JOIN PersonneAbsence pa ON p.id_p = pa.id_personne JOIN Absence a ON pa.id_absence = a.id_m WHERE a.isRetard = ? AND a.id_m = ?";
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(2, absence.getId());
                statement.setBoolean(1, false);
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    Personne personne = personneDAO.mapResultSetToPersonne(resultSet);
                    personnes.add(personne);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return personnes;
    }

    public void updateJustification(int id, boolean isJustified) {
        String query = "UPDATE Absence SET isChecked = ?, isJustified = ? WHERE id_m = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            statement.setBoolean(2, isJustified);
            statement.setInt(3, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public List<Absence> getAbsencesParPersonne(int idPersonne) {
        List<Absence> absences = new ArrayList<>();
        String query = "SELECT a.* FROM Absence a JOIN PersonneAbsence pa ON a.id_m = pa.id_absence WHERE pa.id_personne = ? AND a.isRetard = false";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPersonne);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Absence absence = mapResultSetToAbsence(resultSet);
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }
    public List<Absence> getRetardsParPersonne(int idPersonne) {
        List<Absence> absences = new ArrayList<>();
        String query = "SELECT a.* FROM Absence a JOIN PersonneAbsence pa ON a.id_m = pa.id_absence WHERE pa.id_personne = ? AND a.isRetard = true";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPersonne);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Absence absence = mapResultSetToAbsence(resultSet);
                absences.add(absence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return absences;
    }

}

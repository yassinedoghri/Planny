package model.provider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Planning;

/**
 * Classe Provider pour l'objet Planning
 *
 * @author Yassine Doghri
 */
public class PlanningProvider {

    public static void createTable(Connection c) {
        Statement stmt = null;
        try {
            stmt = c.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS PLANNING ("
                    + "   IDPLANNING INT NOT NULL,"
                    + "   ANNEEPLANNING TEXT UNIQUE NULL,"
                    + "   PRIMARY KEY (IDPLANNING)"
                    + " )";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(FormateurProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Table Planning initialisée");
    }

    public static HashMap<Integer, Planning> getAll(Connection c) {
        try {
            HashMap<Integer, Planning> plannings = new HashMap();

            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PLANNING;");
            while (rs.next()) {
                Planning planning = new Planning();
                planning.setId(rs.getInt("idPlanning"));
                planning.setAnneePlanning(rs.getString("anneePlanning"));
                planning.setListeFormations(FormationProvider.getFormations(planning, c));

                plannings.put(rs.getInt("idPlanning"), planning);
            }
            rs.close();
            stmt.close();

            return plannings;
        } catch (SQLException ex) {
            Logger.getLogger(PlanningProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void insertPlanning(Connection c, Planning planning) {
        try {
            c.setAutoCommit(false);
            Statement stmt = c.createStatement();

            String sql = "INSERT INTO PLANNING (IDPLANNING,ANNEEPLANNING) "
                    + "VALUES (" + planning.getId() + ", "
                    + "'" + planning.getAnneePlanning() + "' );";
            stmt.executeUpdate(sql);

            stmt.close();
            c.commit();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }

}

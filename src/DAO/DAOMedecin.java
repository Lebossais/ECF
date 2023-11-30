package DAO;

import gestion.Medecin;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMedecin extends DAO<Medecin>{

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Medecin obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into medecin ");
        sqlInsertUtilisateur.append("Med_ID, Med_Aggrement, Per_ID ");
        sqlInsertUtilisateur.append("values (null, ?, ?)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setString(1, obj.getAggrement());
            preparedStatement.setInt(2, obj.getPersonne().getPer_ID());

            preparedStatement.executeUpdate();
            requeteok = true;

        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return requeteok;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean delete(Medecin obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from medecin ");
        sqlInsertUtilisateur.append("where Med_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getMed_ID());

            preparedStatement.executeUpdate();
            requeteok = true;
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return requeteok;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean update(Medecin obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update medecin ");
        sqlInsertUtilisateur.append("set Med_Aggrement = ? ");
        sqlInsertUtilisateur.append("where Med_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setString(1, obj.getAggrement());
            preparedStatement.setInt(2, obj.getMed_ID());

            preparedStatement.executeUpdate();
            requeteok = true;

        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return requeteok;
    }

    /**
     *
     * @param pId
     * @return
     */
    @Override
    public Medecin find(Integer pId) {
        DAOPersonne daoPersonne = new DAOPersonne();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from medecin ");
        sqlInsertUtilisateur.append("where Med_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Medecin m;
            while(resultSet.next()) {
                return new Medecin(
                        resultSet.getInt("Med_ID"),
                        daoPersonne.find(resultSet.getInt("Per_ID")),
                        resultSet.getString("Med_Aggrement")
                );
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Medecin> findALL() {
        DAOPersonne daoPersonne = new DAOPersonne();
        ArrayList<Medecin> medecin = new ArrayList<>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from medecin");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                medecin.add(new Medecin(
                        resultSet.getInt("Med_ID"),
                        daoPersonne.find(resultSet.getInt("Per_ID")),
                        resultSet.getString("Med_Aggrement")
                ));
            }

        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }

        return medecin;
    }
}

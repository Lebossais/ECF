package DAO;

import gestion.Specialiste;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOSpecialiste extends DAO<Specialiste> {

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Specialiste obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into specialiste ");
        sqlInsertUtilisateur.append("Spe_ID, Spe_Specialite, Per_ID ");
        sqlInsertUtilisateur.append("values (null,?, ?)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setString(1, obj.getSpecialite());
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
    public boolean delete(Specialiste obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from specialiste ");
        sqlInsertUtilisateur.append("where Spe_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getSpe_ID());

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
    public boolean update(Specialiste obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update specialiste ");
        sqlInsertUtilisateur.append("set Spe_Specialite = ? ");
        sqlInsertUtilisateur.append("where Spe_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setString(1,obj.getSpecialite());
            preparedStatement.setInt(2, obj.getSpe_ID());

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
    public Specialiste find(Integer pId) {
        DAOPersonne daoPersonne = new DAOPersonne();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from specialiste ");
        sqlInsertUtilisateur.append("where Spe_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Specialiste s;
            while(resultSet.next()) {
                return new Specialiste(
                        resultSet.getInt("Spe_ID"),
                        resultSet.getString("Spe_Specialite"),
                        daoPersonne.find(resultSet.getInt("Per_ID"))
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
    public List<Specialiste> findALL() {
        DAOPersonne daoPersonne = new DAOPersonne();
        ArrayList<Specialiste> specialiste = new ArrayList<>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from specialiste");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                specialiste.add(new Specialiste(
                        resultSet.getInt("Spe_ID"),
                        resultSet.getString("Spe_Specialite"),
                        daoPersonne.find(resultSet.getInt("Per_ID"))
                ));
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }

        return specialiste;
    }
}

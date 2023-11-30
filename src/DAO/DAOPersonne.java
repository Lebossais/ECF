package DAO;

import gestion.Personne;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPersonne extends DAO<Personne> {

    /**
     *
     * @param obj
     * @return
     */
    public Integer createPersonne(Personne obj) {
        Integer newId = 0;
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into personne ");
        sqlInsertUtilisateur.append("(Per_ID, Per_Prenom, Per_Nom, Per_Telephone, Per_Email, Adr_ID) ");
        sqlInsertUtilisateur.append("values (null, ?, ?, ?, ?, ?)");


        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString(),PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, obj.getPrenom());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setString(3, obj.getTelephone());
            preparedStatement.setString(4, obj.getEmail());
            preparedStatement.setInt(5, obj.getAdresse().getAdr_ID());

            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()){
                newId = result.getInt(1);
            }

        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return newId;
    }

    @Override
    public boolean create(Personne obj) {
        return false;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean delete(Personne obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from personne ");
        sqlInsertUtilisateur.append("where Per_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getPer_ID());

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
    public boolean update(Personne obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update personne ");
        sqlInsertUtilisateur.append("set Per_Prenom = ?, Per_Nom = ?," +
                " Per_Telephone = ?, Per_Email = ?, Adr_ID = ? ");
        sqlInsertUtilisateur.append("where Per_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setString(1, obj.getPrenom());
            preparedStatement.setString(2, obj.getName());
            preparedStatement.setString(3, obj.getTelephone());
            preparedStatement.setString(4, obj.getEmail());
            preparedStatement.setInt(5, obj.getAdresse().getAdr_ID());
            preparedStatement.setInt(6, obj.getPer_ID());

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
    public Personne find(Integer pId) {
        DAOAdresse daoAdresse = new DAOAdresse();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from personne ");
        sqlInsertUtilisateur.append("where Per_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Personne p;
            while(resultSet.next()) {
                return new Personne(
                        resultSet.getInt("Per_ID"),
                        resultSet.getString("Per_Prenom"),
                        resultSet.getString("Per_Nom"),
                        daoAdresse.find(resultSet.getInt("Adr_ID")),
                        resultSet.getString("Per_Telephone"),
                        resultSet.getString("Per_Email")
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
    public List<Personne> findALL() {
        DAOAdresse daoAdresse = new DAOAdresse();
        ArrayList<Personne> personne = new ArrayList<>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from personne");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                personne.add(new Personne(
                        resultSet.getInt("Per_ID"),
                        resultSet.getString("Per_Prenom"),
                        resultSet.getString("Per_Nom"),
                        daoAdresse.find(resultSet.getInt("Adr_ID")),
                        resultSet.getString("Per_Telephone"),
                        resultSet.getString("Per_Email")
                ));
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return null;
    }
}
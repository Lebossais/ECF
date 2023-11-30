package DAO;

import gestion.Categorie;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCategorie extends DAO<Categorie> {

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Categorie obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into categorie ");
        sqlInsertUtilisateur.append("Cat_ID, Cat_Categorie ");
        sqlInsertUtilisateur.append("values (null,?)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setString(1, obj.getCat_Categorie());

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
    public boolean delete(Categorie obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from categorie ");
        sqlInsertUtilisateur.append("where cat_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getCat_ID());

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
    public boolean update(Categorie obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update categorie ");
        sqlInsertUtilisateur.append("set Cat_Categorie = ? ");
        sqlInsertUtilisateur.append("where Cat_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setString(1,obj.getCat_Categorie());
            preparedStatement.setInt(2, obj.getCat_ID());

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
    public Categorie find(Integer pId) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from categorie ");
        sqlInsertUtilisateur.append("where Cat_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Categorie c;
            while(resultSet.next()) {
                return new Categorie(
                        resultSet.getInt("Cat_ID"),
                        resultSet.getString("Cat_Categorie")
                );
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() + "]");
        }
        return null;
    }

    /**
     *
     * @return
     */
    @Override
    public List findALL() {
        ArrayList<Categorie> categorie = new ArrayList<>();

        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from categorie");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString()))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
            categorie.add(new Categorie(
                    resultSet.getInt("Cat_ID"),
                    resultSet.getString("Cat_Categorie")
            ));
            }
            return categorie;
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }

        return null;
    }
}
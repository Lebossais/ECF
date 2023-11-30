package DAO;

import gestion.Adresse;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOAdresse extends DAO<Adresse> {

    /**
     *
     * @param obj
     * @return
     */
    public Integer createAdresse(Adresse obj) {
        Integer newId = null;
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into adresse ");
        sqlInsertUtilisateur.append("(Adr_Numero_Rue, Adr_Nom_Rue, Adr_Code_Postal, Adr_Ville, Adr_ID) ");
        sqlInsertUtilisateur.append("values (?, ?, ?, ?, null)");


        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString(),PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setInt(1, obj.getNumero_rue());
            preparedStatement.setString(2, obj.getNom_rue());
            preparedStatement.setInt(3, obj.getCodePostal());
            preparedStatement.setString(4, obj.getVille());

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
    public boolean create(Adresse obj){
        return false;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean delete(Adresse obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from adresse ");
        sqlInsertUtilisateur.append("where Adr_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getAdr_ID());

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
    public boolean update(Adresse obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update adresse ");
        sqlInsertUtilisateur.append("set Adr_Numero_Rue = ? ");
        sqlInsertUtilisateur.append(",Adr_Nom_Rue = ? ");
        sqlInsertUtilisateur.append(",Adr_Code_Postal = ? ");
        sqlInsertUtilisateur.append(",Adr_Ville = ? ");
        sqlInsertUtilisateur.append("where Adr_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1,obj.getNumero_rue());
            preparedStatement.setString(2, obj.getNom_rue());
            preparedStatement.setInt(3,obj.getCodePostal());
            preparedStatement.setString(4,obj.getVille());
            preparedStatement.setInt(5,obj.getAdr_ID());

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
    public Adresse find(Integer pId) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from adresse ");
        sqlInsertUtilisateur.append("where Adr_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Adresse a;
            while(resultSet.next()) {
                return new Adresse(
                        resultSet.getInt("Adr_ID"),
                        resultSet.getInt("Adr_Numero_Rue"),
                        resultSet.getString("Adr_Nom_Rue"),
                        resultSet.getInt("Adr_Code_Postal"),
                        resultSet.getString("Adr_Ville")
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
    public List<Adresse> findALL() {
        ArrayList<Adresse> adresse = new ArrayList<>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from adresse");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                adresse.add(new Adresse(
                        resultSet.getInt("Adr_ID"),
                        resultSet.getInt("Adr_Numero_Rue"),
                        resultSet.getString("Adr_Nom_Rue"),
                        resultSet.getInt("Adr_Code_Postal"),
                        resultSet.getString("Adr_Ville")
                ));
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return null;
    }
}

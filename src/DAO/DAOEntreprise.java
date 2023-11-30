package DAO;

import gestion.Entreprise;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOEntreprise extends DAO<Entreprise> {

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Entreprise obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into entreprise ");
        sqlInsertUtilisateur.append("Ent_ID, Ent_Raison_Sociale, Ent_Telephone, Ent_Email, Adr_ID ");
        sqlInsertUtilisateur.append("values (null, ?, ?, ?, ?)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setString(1, obj.getEnt_Raison_Sociale());
            preparedStatement.setString(2, obj.getEnt_Telephone());
            preparedStatement.setString(3, obj.getEnt_Email());
            preparedStatement.setInt(4, obj.getAdresse().getAdr_ID());

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
    public boolean delete(Entreprise obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from entreprise ");
        sqlInsertUtilisateur.append("where Ent_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getEnt_ID());

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
    public boolean update(Entreprise obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update entreprise ");
        sqlInsertUtilisateur.append("set Ent_Raison_Sociale = ?, Ent_Telephone = ?, Ent_Email = ? ");
        sqlInsertUtilisateur.append("where Ent_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setString(1, obj.getEnt_Raison_Sociale());
            preparedStatement.setString(2, obj.getEnt_Telephone());
            preparedStatement.setString(3, obj.getEnt_Email());
            preparedStatement.setInt(4, obj.getEnt_ID());

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
    public Entreprise find(Integer pId) {
        DAOAdresse daoAdresse = new DAOAdresse();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from entreprise ");
        sqlInsertUtilisateur.append("where Ent_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Entreprise e;
            while(resultSet.next()) {
                return new Entreprise(
                        resultSet.getInt("Ent_ID"),
                        resultSet.getString("Ent_Raison_Sociale"),
                        resultSet.getString("Ent_Telephone"),
                        resultSet.getString("Ent_Email"),
                        daoAdresse.find(resultSet.getInt("Adr_ID"))
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
    public List<Entreprise> findALL() {
        DAOAdresse daoAdresse = new DAOAdresse();
        ArrayList<Entreprise> entreprise = new ArrayList<>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from entreprise");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                entreprise.add(new Entreprise(
                        resultSet.getInt("Ent_ID"),
                        resultSet.getString("Ent_Raison_Sociale"),
                        resultSet.getString("Ent_Telephone"),
                        resultSet.getString("Ent_Email"),
                        daoAdresse.find(resultSet.getInt("Adr_ID"))
                ));
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }

        return null;
    }
}

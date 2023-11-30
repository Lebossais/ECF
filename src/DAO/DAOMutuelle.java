package DAO;

import gestion.Adresse;
import gestion.Mutuelle;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMutuelle extends DAO<Mutuelle> {

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Mutuelle obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into mutuelle ");
        sqlInsertUtilisateur.append("Mut_ID, Mut_Departement, Mut_PriseEnCharge, Ent_ID ");
        sqlInsertUtilisateur.append("values (null, ?, ?, ?)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setInt(1, obj.getDepartement());
            preparedStatement.setInt(2, obj.getPrise_En_Charge());
            preparedStatement.setInt(3, obj.getEntreprise().getEnt_ID());

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
    public boolean delete(Mutuelle obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from mutuelle ");
        sqlInsertUtilisateur.append("where Mut_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getMut_ID());

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
    public boolean update(Mutuelle obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update Mutuelle ");
        sqlInsertUtilisateur.append("set Mut_Departement, Mut_PriseEnCharge, ");
        sqlInsertUtilisateur.append("where Mut_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1,obj.getDepartement());
            preparedStatement.setInt(2, obj.getPrise_En_Charge());
            preparedStatement.setInt(3, obj.getMut_ID());

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
    public Mutuelle find(Integer pId) {
        DAOEntreprise daoEntreprise = new DAOEntreprise();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from mutuelle ");
        sqlInsertUtilisateur.append("where Mut_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Mutuelle m;
            while(resultSet.next()) {
                return new Mutuelle(
                        resultSet.getInt("Mut_ID"),
                        daoEntreprise.find(resultSet.getInt("Ent_ID")),
                        resultSet.getInt("Mut_Departement"),
                        resultSet.getInt("Mut_PriseEnCharge")
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
    public List<Mutuelle> findALL() {
        ArrayList<Mutuelle> mutuelle = new ArrayList<>();
        DAOEntreprise daoEntreprise = new DAOEntreprise();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from mutuelle");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                mutuelle.add(new Mutuelle(
                        resultSet.getInt("Mut_ID"),
                        daoEntreprise.find(resultSet.getInt("Ent_ID")),
                        resultSet.getInt("Mut_Departement"),
                        resultSet.getInt("Mut_PriseEnCharge")
                ));
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }

        return mutuelle;
    }
}

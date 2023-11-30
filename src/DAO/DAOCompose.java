package DAO;

import gestion.Compose;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOCompose extends DAO<Compose>{

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Compose obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into compose ");
        sqlInsertUtilisateur.append("Ord_ID, Medi_ID, Compose_Qte ");
        sqlInsertUtilisateur.append("values (?, ?, ?)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setInt(1, obj.getOrdonnance().getOrd_ID());
            preparedStatement.setInt(2, obj.getMedicament().getMedi_ID());
            preparedStatement.setInt(3, obj.getCompose_Qte());

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
    public boolean delete(Compose obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from compose ");
        sqlInsertUtilisateur.append("where Ord_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getOrdonnance().getOrd_ID());

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
    public boolean update(Compose obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update update ");
        sqlInsertUtilisateur.append("set Medi_ID = ?, Compose_Qte = ? ");
        sqlInsertUtilisateur.append("where Ord_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getMedicament().getMedi_ID());
            preparedStatement.setInt(2, obj.getCompose_Qte());
            preparedStatement.setInt(3, obj.getOrdonnance().getOrd_ID());

            preparedStatement.executeUpdate();
            requeteok = true;

        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return requeteok;
    }

    @Override
    public Compose find(Integer pId) {
        return null;
    }

    /**
     *
     * @param pId
     * @return
     */
    public List<Compose> findwhere(Integer pId) {
        DAOOrdonnance daoOrdonnance = new DAOOrdonnance();
        DAOMedicament daoMedicament = new DAOMedicament();
        ArrayList<Compose> compose = new ArrayList<>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from compose ");
        sqlInsertUtilisateur.append("where Ord_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();
            Compose c;

            while(resultSet.next()) {
                compose.add(new Compose(
                        daoOrdonnance.find(resultSet.getInt("Ord_ID")),
                        daoMedicament.find(resultSet.getInt("Medi_ID")),
                        resultSet.getInt("Compose_Qte")
                ));
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return compose;
    }

    /**
     *
     * @return
     */
    @Override
    public List<Compose> findALL() {
        ArrayList<Compose> compose = new ArrayList<>();
        DAOOrdonnance daoOrdonnance = new DAOOrdonnance();
        DAOMedicament daoMedicament = new DAOMedicament();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from compose");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                compose.add(new Compose(
                        daoOrdonnance.find(resultSet.getInt("Ord_ID")),
                        daoMedicament.find(resultSet.getInt("Med_ID")),
                        resultSet.getInt("Compose_Qte")
                ));
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }

        return null;
    }
}

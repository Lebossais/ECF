package DAO;

import gestion.Ordonnance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOOrdonnance extends DAO<Ordonnance> {

    /**
     *
     * @param obj
     * @return
     */
    public Integer createOrdonnance(Ordonnance obj) {
        Integer newId = null;
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into ordonnance ");
        sqlInsertUtilisateur.append("Ord_ID, Ord_Num, Ord_Date, Cli_ID, Med_ID, Spe_ID ");
        sqlInsertUtilisateur.append("values (null, ?, ?, ?, ?,? )");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setString(1, obj.getOrd_Num());
            preparedStatement.setString(2, obj.getOrd_Date());
            preparedStatement.setInt(3, obj.getClient().getCli_ID());
            preparedStatement.setInt(4, obj.getMedecin().getMed_ID());
            preparedStatement.setInt(5, obj.getSpecialiste().getSpe_ID());


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
    public boolean create(Ordonnance obj) {
        return false;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean delete(Ordonnance obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from ordonnance ");
        sqlInsertUtilisateur.append("where Ord_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getOrd_ID());

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
    public boolean update(Ordonnance obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update ordonnance ");
        sqlInsertUtilisateur.append("set Ord_Num = ?, Ord_Date = ?, Cli_ID = ?, Med_ID = ?, Spe_ID = ? ");
        sqlInsertUtilisateur.append("where Ord_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setString(1, obj.getOrd_Num());
            preparedStatement.setString(2, obj.getOrd_Date());
            preparedStatement.setInt(3, obj.getClient().getCli_ID());
            preparedStatement.setInt(4, obj.getMedecin().getMed_ID());
            preparedStatement.setInt(5, obj.getSpecialiste().getSpe_ID());
            preparedStatement.setInt(6, obj.getOrd_ID());

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
    public Ordonnance find(Integer pId) {
        DAOClient daoClient = new DAOClient();
        DAOMedecin daoMedecin = new DAOMedecin();
        DAOSpecialiste daoSpecialiste = new DAOSpecialiste();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from ordonnance ");
        sqlInsertUtilisateur.append("where Ord_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Ordonnance o;
            while(resultSet.next()) {
                return new Ordonnance(resultSet.getInt("Ord_ID"),
                        resultSet.getString("Ord_Num"),
                        daoClient.find(resultSet.getInt("Cli_ID")),
                        daoMedecin.find(resultSet.getInt("Med_ID")),
                        daoSpecialiste.find(resultSet.getInt("Spe_ID")),
                        resultSet.getString("Ord_Date"));
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
    public List<Ordonnance> findALL() {
        DAOClient daoClient = new DAOClient();
        DAOMedecin daoMedecin = new DAOMedecin();
        DAOSpecialiste daoSpecialiste = new DAOSpecialiste();
        ArrayList<Ordonnance> ordonnance = new ArrayList<Ordonnance>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from ordonnance");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
            ordonnance.add(new Ordonnance(
                    resultSet.getInt("Ord_ID"),
                    resultSet.getString("Ord_Num"),
                    daoClient.find(resultSet.getInt("Cli_ID")),
                    daoMedecin.find(resultSet.getInt("Med_ID")),
                    daoSpecialiste.find(resultSet.getInt("Spe_ID")),
                    resultSet.getString("Ord_Date")));
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return ordonnance;
    }

    /**
     *
     * @param pId
     * @return
     */
    public List<Ordonnance> findALLwhere(Integer pId) {
        DAOClient daoClient = new DAOClient();
        DAOMedecin daoMedecin = new DAOMedecin();
        DAOSpecialiste daoSpecialiste = new DAOSpecialiste();
        ArrayList<Ordonnance> ordonnance = new ArrayList<Ordonnance>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from ordonnance ");
        sqlInsertUtilisateur.append("inner join compose on ordonnance.Ord_ID = compose.Ord_ID ");
        sqlInsertUtilisateur.append("where Ord_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                ordonnance.add(new Ordonnance(
                        resultSet.getInt("Ord_ID"),
                        resultSet.getString("Ord_Num"),
                        daoClient.find(resultSet.getInt("Cli_ID")),
                        daoMedecin.find(resultSet.getInt("Med_ID")),
                        daoSpecialiste.find(resultSet.getInt("Spe_ID")),
                        resultSet.getString("Ord_Date")));
            }
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return ordonnance;
    }
}

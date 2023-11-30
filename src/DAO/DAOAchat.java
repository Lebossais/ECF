package DAO;

import gestion.Achat;
import utilitaire.LoggerJava;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOAchat extends DAO<Achat>{


    /**
     *
     * @param obj
     * @return
     */
    public Integer createAchatPanier(Achat obj) {
        Integer newId = 0;
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into achat ");
        sqlInsertUtilisateur.append("(Achat_ID, Achat_Date, Cli_ID, Ord_ID) ");
        sqlInsertUtilisateur.append("values (null, ?, ?, null)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString(), PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, String.valueOf(obj.getAchat_Date()));
            preparedStatement.setInt(2, obj.getClient().getCli_ID());

            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()){
                newId = result.getInt(1);
            }


        } catch (SQLException sqle) {
            LoggerJava log = new LoggerJava();
            log.setLoggerJava(sqle.getMessage());
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return newId;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Achat obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into achat ");
        sqlInsertUtilisateur.append("(Achat_ID, Achat_Date, Cli_ID, Ord_ID) ");
        sqlInsertUtilisateur.append("values (null, ?, ?, ?)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setString(1, String.valueOf(obj.getAchat_Date()));
            preparedStatement.setInt(2, obj.getClient().getCli_ID());
            preparedStatement.setInt(3, obj.getOrdonnance().getOrd_ID());

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
    public boolean delete(Achat obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from achat ");
        sqlInsertUtilisateur.append("where Achat_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getAchat_ID());

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
    public boolean update(Achat obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update achat ");
        sqlInsertUtilisateur.append("set Achat_Date = ?, Cli_ID = ?, Ord_ID = ? ");
        sqlInsertUtilisateur.append("where Achat_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setString(1, String.valueOf(obj.getAchat_Date()));
            preparedStatement.setInt(2, obj.getClient().getCli_ID());
            preparedStatement.setInt(3, obj.getOrdonnance().getOrd_ID());
            preparedStatement.setInt(4, obj.getAchat_ID());


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
    public Achat find(Integer pId) {
        DAOClient daoClient = new DAOClient();
        DAOOrdonnance daoOrdonnance = new DAOOrdonnance();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from achat ");
        sqlInsertUtilisateur.append("where Achat_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Achat a;
            while(resultSet.next()) {
                return new Achat(
                        resultSet.getInt("Achat_ID"),
                        resultSet.getString("Achat_Date"),
                        daoClient.find(resultSet.getInt("Cli_ID")),
                        daoOrdonnance.find(resultSet.getInt("Ord_ID"))
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
    public List<Achat> findALL() {
        DAOClient daoClient = new DAOClient();
        DAOOrdonnance daoOrdonnance = new DAOOrdonnance();
        ArrayList<Achat> achat = new ArrayList<>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from achat");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                achat.add(new Achat(
                        resultSet.getInt("Achat_ID"),
                        resultSet.getString("Achat_Date"),
                        daoClient.find(resultSet.getInt("Cli_ID")),
                        daoOrdonnance.find(resultSet.getInt("Ord_ID"))
                ));
            }
            return achat;
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }

        return achat;
    }
}

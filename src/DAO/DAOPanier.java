package DAO;

import gestion.Panier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPanier extends DAO<Panier>{

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Panier obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into panier ");
        sqlInsertUtilisateur.append("(Achat_ID, Medi_ID, Panier_Qte) ");
        sqlInsertUtilisateur.append("values (?, ?, ?)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setInt(1, obj.getAchat().getAchat_ID());
            preparedStatement.setInt(2, obj.getMedicament().getMedi_ID());
            preparedStatement.setInt(3, obj.getPanier_Qte());

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
    public boolean delete(Panier obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from panier ");
        sqlInsertUtilisateur.append("where Achat_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getAchat().getAchat_ID());

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
    public boolean update(Panier obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update panier ");
        sqlInsertUtilisateur.append("set Medi_ID = ?, Panier_Qte = ? ");
        sqlInsertUtilisateur.append("where Achat_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getMedicament().getMedi_ID());
            preparedStatement.setInt(2, obj.getPanier_Qte());
            preparedStatement.setInt(3, obj.getAchat().getAchat_ID());

            preparedStatement.executeUpdate();
            requeteok = true;

        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return requeteok;
    }

    @Override
    public Panier find(Integer pId) {
        DAOAchat daoAchat = new DAOAchat();
        DAOMedicament daoMedicament = new DAOMedicament();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from panier ");
        sqlInsertUtilisateur.append("where Achat_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Panier p;
            while(resultSet.next()) {
                return new Panier(
                        daoAchat.find(resultSet.getInt("Achat_ID")),
                        daoMedicament.find(resultSet.getInt("Medi_ID")),
                        resultSet.getInt("Panier_Qte")
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
    public List<Panier> findALL() {
        DAOAchat daoAchat = new DAOAchat();
        DAOMedicament daoMedicament = new DAOMedicament();
        ArrayList<Panier> panier = new ArrayList<>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from panier");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                panier.add(new Panier(
                        daoAchat.find(resultSet.getInt("Achat_ID")),
                        daoMedicament.find(resultSet.getInt("Medi_ID")),
                        resultSet.getInt("Panier_Qte")
                ));
            }
            return panier;
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }

        return null;
    }

    /**
     *
     * @param test
     * @return
     */
    public boolean deletebyID(int test) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from panier ");
        sqlInsertUtilisateur.append("where achat_ID = ? && Medi_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, test);

            preparedStatement.executeUpdate();
            requeteok = true;
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return requeteok;
    }
}

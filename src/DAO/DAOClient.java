package DAO;

import gestion.Client;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOClient extends DAO<Client> {

    /**
     *
     * @param obj
     * @return
     */
    public Integer createClient(Client obj) {
        Integer newId = null;
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into client ");
        sqlInsertUtilisateur.append("(Cli_Date_Naissance, Cli_Numero_Secu, Per_ID, Med_ID, Spe_ID, Mut_ID) ");
        sqlInsertUtilisateur.append("values (?, ?, ?, ?, ?, ?)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString(),PreparedStatement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, obj.getDate_Naissance());
            preparedStatement.setString(2, obj.getNumero_Secu());
            preparedStatement.setInt(3, obj.getPersonne().getPer_ID());
            preparedStatement.setInt(4, obj.getMedecin_Traitant().getMed_ID());
            preparedStatement.setInt(6, obj.getMutuelle().getMut_ID());

            if (obj.getSpecialiste() ==  null){
                preparedStatement.setInt(5, Integer.parseInt("null"));
            }else {
                preparedStatement.setInt(5, obj.getSpecialiste().getSpe_ID());
            }
            preparedStatement.executeUpdate();
            ResultSet result = preparedStatement.getGeneratedKeys();
            if (result.next()){
                newId = result.getInt("Cli_ID");
            }
            requeteok = true;

        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }
        return newId;
    }
    @Override
    public boolean create(Client obj) {
        return false;
    }

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean delete(Client obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from personne ");
        sqlInsertUtilisateur.append("where Per_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getPersonne().getPer_ID());

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
    public boolean update(Client obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update client ");
        sqlInsertUtilisateur.append("set Cli_Date_Naissance = ?, Cli_Numero_Secu = ?, " +
                "Per_ID = ?, Med_ID = ?, Spe_ID = ?, Mut_ID = ? ");
        sqlInsertUtilisateur.append("where Cli_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setString(1, obj.getDate_Naissance());
            preparedStatement.setString(2, obj.getNumero_Secu());
            preparedStatement.setInt(3, obj.getPersonne().getPer_ID());
            preparedStatement.setInt(4, obj.getMedecin_Traitant().getMed_ID());
            preparedStatement.setInt(5, obj.getSpecialiste().getSpe_ID());
            preparedStatement.setInt(6, obj.getMutuelle().getMut_ID());
            preparedStatement.setInt(7, obj.getCli_ID());

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
    public Client find(Integer pId) {
        DAOPersonne daoPersonne = new DAOPersonne();
        DAOMutuelle daoMutuelle = new DAOMutuelle();
        DAOMedecin daoMedecin = new DAOMedecin();
        DAOSpecialiste daoSpecialiste = new DAOSpecialiste();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from client ");
        sqlInsertUtilisateur.append("where Cli_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Client c;
            while(resultSet.next()) {
                return new Client(resultSet.getInt("Cli_ID"),
                        daoPersonne.find(resultSet.getInt("Per_ID")),
                        resultSet.getString("Cli_Date_Naissance"),
                        resultSet.getString("Cli_Numero_Secu"),
                        daoMutuelle.find(resultSet.getInt("Mut_ID")),
                        daoMedecin.find(resultSet.getInt("Med_ID")),
                        daoSpecialiste.find(resultSet.getInt("Spe_ID"))
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
    public List<Client> findALL() {
        DAOPersonne daoPersonne = new DAOPersonne();
        DAOMutuelle daoMutuelle = new DAOMutuelle();
        DAOMedecin daoMedecin = new DAOMedecin();
        DAOSpecialiste daoSpecialiste = new DAOSpecialiste();
        ArrayList<Client> client = new ArrayList<>();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from client");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                client.add(new Client(
                        resultSet.getInt("Cli_ID"),
                        daoPersonne.find(resultSet.getInt("Per_ID")),
                        resultSet.getString("Cli_Date_Naissance"),
                        resultSet.getString("Cli_Numero_Secu"),
                        daoMutuelle.find(resultSet.getInt("Mut_ID")),
                        daoMedecin.find(resultSet.getInt("Med_ID")),
                        daoSpecialiste.find(resultSet.getInt("Spe_ID"))
                ));
            }
            return client;
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
        sqlInsertUtilisateur.append("Delete from client ");
        sqlInsertUtilisateur.append("where Cli_ID = ?");

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


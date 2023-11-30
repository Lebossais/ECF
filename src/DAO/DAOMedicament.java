package DAO;

import gestion.Medicament;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOMedicament extends DAO<Medicament> {

    /**
     *
     * @param obj
     * @return
     */
    @Override
    public boolean create(Medicament obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("insert into medicament ");
        sqlInsertUtilisateur.append("Medi_ID, Medi_Nom, Medi_Prix, Medi_DateMiseService, Cat_ID ");
        sqlInsertUtilisateur.append("values (null, ?, ?, ?, ?)");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())) {

            preparedStatement.setString(1, obj.getNom());
            preparedStatement.setInt(2, obj.getPrix());
            preparedStatement.setString(3, obj.getDate_Mise_en_Service());
            preparedStatement.setInt(4, obj.getCategorie().getCat_ID());

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
    public boolean delete(Medicament obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Delete from medicament ");
        sqlInsertUtilisateur.append("where Medi_ID = ?");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, obj.getMedi_ID());

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
    public boolean update(Medicament obj) {
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("Update medicament ");
        sqlInsertUtilisateur.append("set Medi_Nom = ?, Medi_Prix = ?, Medi_DateMiseService = ?, Cat_ID = ? ");
        sqlInsertUtilisateur.append("where Medi_ID = ? ");

        boolean requeteok = false;

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setString(1,obj.getNom());
            preparedStatement.setInt(2, obj.getPrix());
            preparedStatement.setString(3,obj.getDate_Mise_en_Service());
            preparedStatement.setInt(4, obj.getCategorie().getCat_ID());
            preparedStatement.setInt(5, obj.getMedi_ID());

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
    public Medicament find(Integer pId) {
        DAOCategorie daoCategorie = new DAOCategorie();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from medicament ");
        sqlInsertUtilisateur.append("where Medi_ID = ?");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            preparedStatement.setInt(1, pId);

            ResultSet resultSet = preparedStatement.executeQuery();

            Medicament m;
            while(resultSet.next()) {
                return new Medicament(
                        resultSet.getInt("Medi_ID"),
                        resultSet.getString("Medi_Nom"),
                        daoCategorie.find(resultSet.getInt("Cat_ID")),
                        resultSet.getInt("Medi_Prix"),
                        resultSet.getString("Medi_DateMiseService")
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
    public List<Medicament> findALL() {
        ArrayList<Medicament> medicament = new ArrayList<>();
        DAOCategorie daoCategorie = new DAOCategorie();
        StringBuilder sqlInsertUtilisateur = new StringBuilder();
        sqlInsertUtilisateur.append("select * from medicament");

        try (PreparedStatement preparedStatement =
                     this.connect.prepareStatement(sqlInsertUtilisateur.toString())){

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                medicament.add(new Medicament(
                        resultSet.getInt("Medi_ID"),
                        resultSet.getString("Medi_Nom"),
                        daoCategorie.find(resultSet.getInt("Cat_ID")),
                        resultSet.getInt("Medi_Prix"),
                        resultSet.getString("Medi_DateMiseService")
                ));
            }
            return medicament;
        } catch (SQLException sqle) {
            System.out.println("RelationWithDB erreur" + sqle.getMessage()
                    + "[SQL error code :" + sqle.getSQLState() +"]");
        }

        return medicament;
    }
}

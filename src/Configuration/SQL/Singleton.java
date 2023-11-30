package Configuration.SQL;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Singleton {

    private static final String PATHCONF = "C:\\PROJET\\JAVA\\Sparadrap-main\\ressources\\confDB.properties";
    private static final Properties props = new Properties();
    private static Connection connection;

    private Singleton()
    {

        try {
              FileInputStream file = new FileInputStream(PATHCONF);

            props.load(file);
            Class.forName( props.getProperty("jdbc.driver.class") );
            props.setProperty("user", props.getProperty("jdbc.login"));
            props.setProperty("password", props.getProperty("jdbc.password"));

            connection = DriverManager.getConnection(props.getProperty("jdbc.url"),props);
            System.out.println("Connection établie");

        } catch (HeadlessException | IOException | SQLException | ClassNotFoundException e)
        {
            System.out.println("RelationwithDB connection " + e.getMessage());
        }

    }
    private static Connection getConnection() {
        return connection;
    }

    public static Connection getInstanceDB() {
        if (getConnection() == null) {
            new Singleton();
            System.out.println("RelationWithDB infos : Connection etablished");
        }
        return getConnection();
    }

    public static void closeInstanceDB() {
        try {
            Singleton.getConnection().close();
            System.out.println("RelationWithDB infos : Connection terminated");
        }
        catch (SQLException sqle){
            System.out.println("RelationWithDB erreur : " + sqle.getMessage() + " [SQL errore code : " + sqle.getSQLState() + " ] " );
        }
    }

    public static void RequeteTest(Connection con) {
        try {
            String test = "select * from Personne";
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery(test);

            while (resultSet.next()){
                System.out.println("Resultat : " + resultSet.getInt("Per_ID") + "-" + resultSet.getString("Per_Prenom"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erreur");
        }
    }
}

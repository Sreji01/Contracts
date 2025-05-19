/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package respository.db;

import domain.OpstiDomenskiObjekat;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Sreja
 */
public class DatabaseBroker {

    private static DatabaseBroker instance;
    private Connection connection;

    private DatabaseBroker() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("dbconfig.properties"));
            String url = properties.getProperty("url");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static DatabaseBroker getInstance() {
        if (instance == null) {
            instance = new DatabaseBroker();
        }
        return instance;
    }

    public PreparedStatement insert(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "INSERT INTO " + odo.getTableName() + " (" + odo.getColumnsForInsert() + ") VALUES (" + odo.getParamsForInsert() + ")";
        System.out.println(upit);
        PreparedStatement ps = connection.prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);
        ps.executeUpdate();
        return ps;
    }

    public List<OpstiDomenskiObjekat> selectMany(OpstiDomenskiObjekat odo) throws SQLException {
        List<OpstiDomenskiObjekat> ls = new ArrayList<>();
        String upit = "SELECT * FROM " + odo.getTableName() + " " + odo.alijas() + " " + odo.join() + " " + odo.getWhereCondition();
        System.out.println(upit);
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            ls.add(odo.getNewRecord(rs));
        }
        return ls;
    }

    public OpstiDomenskiObjekat selectOne(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "SELECT * FROM " + odo.getTableName() + " " + odo.alijas() + " " + odo.join() + " WHERE " + odo.getPrimaryKey();
        Statement s = connection.createStatement();
        ResultSet rs = s.executeQuery(upit);
        while (rs.next()) {
            odo = odo.getNewRecord(rs);
        }
        return odo;
    }

    public void update(OpstiDomenskiObjekat odo) throws SQLException {
        String upit = "UPDATE " + odo.getTableName() +" " + odo.alijas() + " SET " + odo.setAtrValue() + " WHERE " + odo.getPrimaryKey();
        PreparedStatement ps = connection.prepareStatement(upit);
        ps.executeUpdate();
    }
    
    public void delete(OpstiDomenskiObjekat odo) throws SQLException{
        String upit = "DELETE FROM " +odo.getTableName() +" " + " WHERE " +odo.getDeleteCondition();
        System.out.println(upit);
        Statement s = connection.createStatement();
        s.executeUpdate(upit);
    }

}

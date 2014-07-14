/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skuvalidator;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rachit
 */
public class DbHandler {
    
    private static final String CONNECTION_STRING = "jdbc:sqlite:database"  + 
                       System.getProperty("file.separator") + "validator_db.db";
    
    private Connection connection;
    
    private PreparedStatement statement;
    
    /* Constructor */
    public DbHandler() {
        try {

            connection = DriverManager.getConnection(CONNECTION_STRING);
            
            // Create the tables if they do not exist.
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "+ 
                              "profile_list(" +
                              "domain_name TEXT," +
                              "search_term TEXT," +
                              "profile_id INTEGER PRIMARY KEY AUTOINCREMENT)");
            statement.execute();
            
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS "+ 
                              "sku_list(" +
                              "sku_id INTEGER," +
                              "sku_value TEXT, " + 
                              "FOREIGN KEY(sku_id) REFERENCES profile_list(profle_id))");
            
            statement.execute();

        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean createEntry(String domainName, String searchTerm, String SKUList) {
        String insertString = "INSERT INTO profile_list VALUES(?, ?, NULL)";
        ResultSet generatedKeys = null;
               
        try {
            statement = connection.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, domainName);
            statement.setString(2, searchTerm);
            System.out.println(statement.toString());
            int affectedRows = statement.executeUpdate();
            if(affectedRows == 0)
                return false;
            
            generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
            {
                long SKUId = generatedKeys.getLong(1);
                String[] SKUValues = SKUList.split(",");
                insertString = "INSERT INTO sku_list VALUES(?, ?)";
                statement = connection.prepareStatement(insertString);
                for (String string : SKUValues) {
                    statement.setString(1, "" + SKUId);
                    statement.setString(2, string);
                    statement.executeUpdate();
                }
                statement.close();
                connection.close();
                generatedKeys.close();
            }
            else
                return false;
            
        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }

    public boolean modifyEntry() {
        return true;
    }

    public boolean deleteEntry() {
        return true;
    }

    public List getAllEntries() {
        return null;
    }
    
}

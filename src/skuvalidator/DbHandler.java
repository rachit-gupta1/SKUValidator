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
import java.util.ArrayList;
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
                              "user_email TEXT," +
                              "domain_name TEXT," +
                              "search_term TEXT," +
                              "timestamp TEXT," +
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

    public boolean createEntry(String userEmail, String domainName, String searchTerm, String SKUList, String timeStamp) {
        String insertString = "INSERT INTO profile_list VALUES(?, ?, ?, ?, NULL)";
        ResultSet generatedKeys = null;
               
        try {
            statement = connection.prepareStatement(insertString, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, userEmail);
            statement.setString(2, domainName);
            statement.setString(3, searchTerm);
            statement.setString(4, timeStamp);
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

    public List<ProfileDetails> getAllEntries() {
        List<ProfileDetails> resultList = new ArrayList<ProfileDetails>();
        try {
            String queryString = "SELECT * FROM profile_list";
            statement = connection.prepareStatement(queryString);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String id = rs.getString("profile_id");
                String email = rs.getString("user_email");
                String domain = rs.getString("domain_name");
                String search = rs.getString("search_term");
                String time = rs.getString("timestamp");
                String sku = "";
                String skuQuery = "SELECT sku_value FROM sku_list WHERE sku_id=" + id;
                statement = connection.prepareStatement(skuQuery);
                ResultSet skuList = statement.executeQuery();
                while(skuList.next()) {
                    sku = sku + skuList.getString("sku_value") + ",";
                }
                sku = sku.substring(0, sku.length() - 1);
                ProfileDetails details = new ProfileDetails(id, email, domain, search, time, sku);
                resultList.add(details);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultList;
    }
    
}

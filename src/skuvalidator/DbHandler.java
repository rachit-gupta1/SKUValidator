/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skuvalidator;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
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
    
    private Statement statement;
    
    /* Constructor */
    public DbHandler() {
        try {

            connection = DriverManager.getConnection(CONNECTION_STRING);
            statement = connection.createStatement();
            statement.setQueryTimeout(30);          // set timeout to 30s
            
            // Create the table if it does not exist.
            statement.execute("create table if not exists validator_table(" +
                              "domain_name text," +
                              "search_term text," +
                              "sku_list text)");

        } catch (SQLException ex) {
            Logger.getLogger(DbHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean createEntry() {
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

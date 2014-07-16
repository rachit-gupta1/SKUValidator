/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skuvalidator;

/**
 *
 * @author akshat
 */
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class HttpRequestHandler {
    
    HttpURLConnection connection;
    String url;
    
    public HttpRequestHandler() {
        url = "";
    }

    public HttpRequestHandler(String urlString) {
        url = urlString;

    }

    public void SetUrl(String link) {
        url = link;
    }

    public String ExecuteRequest() {
        try { 
            if(!url.endsWith("/"))
                url += "/";
            URL URLObject = new URL(url);
            connection = (HttpURLConnection) URLObject.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Connection", "keep-alive");
            connection.connect();
            int code = connection.getResponseCode();
            while (code == 401) {
                //Authentication required
                JPanel userPanel = new JPanel();
                userPanel.setLayout(new GridLayout(2, 2));
                JLabel usernameLbl = new JLabel("Username:");
                JLabel passwordLbl = new JLabel("Password:");
                JTextField username = new JTextField();
                JPasswordField passwordFld = new JPasswordField();
                userPanel.add(usernameLbl);
                userPanel.add(username);
                userPanel.add(passwordLbl);
                userPanel.add(passwordFld);
                int input = JOptionPane.showConfirmDialog(null, userPanel, "Authentication Required",                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                final String userName = username.getText();
                final String password = passwordFld.getText();

                if (input == JOptionPane.OK_OPTION) {
                    String userPassword = userName + ":" + password;                   
                    String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
                    connection = (HttpURLConnection) URLObject.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Connection", "keep-alive");
                    connection.setRequestProperty("Authorization", "Basic " + encoding);
                    code = connection.getResponseCode();
                    if (code==200) {
                        break;
                    } else {
                        //Invalid user
                        JOptionPane.showMessageDialog(null, "Invalid User");
                    }    
                }
            }
            if (code == 404) {
                JOptionPane.showMessageDialog(null, "Domain Name not found");
            }
            else if(code ==200) {
                JOptionPane.showMessageDialog(null, "Authorised user");
                InputStream content = (InputStream) connection.getInputStream();
                BufferedReader in = new BufferedReader(new InputStreamReader(content));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                return (sb.toString());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Domain Name not found");
            e.printStackTrace();
        }
        return "";
    }
    
}

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

    URL url;
    HttpURLConnection connection;
    String URL;

    public HttpRequestHandler() {
        URL = "";

    }

    public void SetUrl(String link) {
        URL = link;

    }

    public void ExecuteRequest() {
        try {

            url = new URL(URL);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.connect();
            int code = connection.getResponseCode();

            if (code == 401) {
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
                int input = JOptionPane.showConfirmDialog(null, userPanel, "Authentication Required", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                final String userName = username.getText();
                final String password = passwordFld.getText();

                if (input == JOptionPane.OK_OPTION) {
                    String userPassword = userName + ":" + password;
                    String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setRequestProperty("Authorization", "Basic " + encoding);
                    int auth = connection.getResponseCode();

                    if (auth == 200) {
                        //Valid username and password
                        JOptionPane.showMessageDialog(null, "Authorised user");
                        InputStream content = (InputStream) connection.getInputStream();
                        BufferedReader in = new BufferedReader(new InputStreamReader(content));
                        String line;
                        while ((line = in.readLine()) != null) {
                            System.out.println(line);
                        }
                    } else {
                        //Invalid user
                        JOptionPane.showMessageDialog(null, "Invalid User");
                        ExecuteRequest();
                    }
                }



            } else if (code == 404) {
                JOptionPane.showMessageDialog(null, "Domain Name not found");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Domain Name not found");
            e.printStackTrace();

        }
    }
}

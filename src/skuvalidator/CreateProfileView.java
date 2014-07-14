/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skuvalidator;

import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author rachit
 */
public class CreateProfileView extends JPanel{
    
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField domainTextField;
    private javax.swing.JCheckBox authCheckBox;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JPasswordField passwordTextField;
    private javax.swing.JTextField usernameTextField;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton uploadTestFileButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel mainPanel;
    
    private boolean isTestFileValid;
    private boolean isTestFileUploaded;
    private String testFileContents;

    public CreateProfileView(javax.swing.JPanel parent) {
        
        mainPanel = parent;
        isTestFileValid = false;
        isTestFileUploaded = false;
        
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(skuvalidator.SKUValidatorApp.class).getContext().getResourceMap(SKUValidatorView.class);
        
        jLabel1 = new javax.swing.JLabel();
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N
        
        domainTextField = new javax.swing.JTextField();
        domainTextField.setText(resourceMap.getString("domainTextField.text")); // NOI18N
        domainTextField.setName("domainTextField"); // NOI18N
        
        authCheckBox = new javax.swing.JCheckBox();
        authCheckBox.setText(resourceMap.getString("authCheckBox.text")); // NOI18N
        authCheckBox.setName("authCheckBox"); // NOI18N
        authCheckBox.setRolloverEnabled(false);
        authCheckBox.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                authCheckBoxStateChanged(evt);
            }
        });        
        
        usernameLabel = new javax.swing.JLabel();
        usernameLabel.setText(resourceMap.getString("usernameLabel.text")); // NOI18N
        usernameLabel.setName("usernameLabel"); // NOI18N
        usernameLabel.setVisible(false);
        
        passwordLabel = new javax.swing.JLabel();
        passwordLabel.setText(resourceMap.getString("passwordLabel.text")); // NOI18N
        passwordLabel.setName("passwordLabel"); // NOI18N
        passwordLabel.setVisible(false);
        
        passwordTextField = new javax.swing.JPasswordField();
        passwordTextField.setText(resourceMap.getString("passwordTextField.text")); // NOI18N
        passwordTextField.setName("passwordTextField"); // NOI18N
        passwordTextField.setVisible(false);
        
        usernameTextField = new javax.swing.JTextField();
        usernameTextField.setText(resourceMap.getString("usernameTextField.text")); // NOI18N
        usernameTextField.setName("usernameTextField"); // NOI18N
        usernameTextField.setVisible(false);
        
        submitButton = new javax.swing.JButton();
        submitButton.setText(resourceMap.getString("submitButton.text")); // NOI18N
        submitButton.setName("submitButton"); // NOI18N
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        
        uploadTestFileButton = new javax.swing.JButton();
        uploadTestFileButton.setText(resourceMap.getString("uploadTestFileButton.text")); // NOI18N
        uploadTestFileButton.setName("uploadTestFileButton"); // NOI18N
        uploadTestFileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadTestFileButtonActionPerformed(evt);
            }
        });
        
        filler1 = new javax.swing.Box.Filler(null, null, null);
        filler1.setName("filler1"); // NOI18N
        
        cancelButton = new javax.swing.JButton();
        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        
        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(this);
        setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                        .addComponent(domainTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(authCheckBox)
                    .addGroup(Panel2Layout.createSequentialGroup()
                        .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(usernameLabel)
                            .addComponent(passwordLabel))
                        .addGap(18, 18, 18)
                        .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)))
                    .addGroup(Panel2Layout.createSequentialGroup()
                        .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(uploadTestFileButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(111, 111, 111)
                        .addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        Panel2Layout.setVerticalGroup(
            Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(domainTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(authCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(passwordLabel)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(uploadTestFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(submitButton)
                        .addComponent(cancelButton)))
                .addContainerGap(45, Short.MAX_VALUE))
        );

    }
    
    private void authCheckBoxStateChanged(javax.swing.event.ChangeEvent evt) {
        // TODO add your handling code here:
        if(!usernameLabel.isVisible()) {
            usernameLabel.setVisible(true);
            usernameTextField.setVisible(true);
            passwordLabel.setVisible(true);
            passwordTextField.setVisible(true);
        }
        else {
            usernameLabel.setVisible(false);
            usernameTextField.setVisible(false);
            passwordLabel.setVisible(false);
            passwordTextField.setVisible(false);
        }
    }
    
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        
        String domainName = domainTextField.getText();
        
        if("".equals(domainName)) {
            JOptionPane.showMessageDialog(this, "The domain field cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!isTestFileUploaded) {
            JOptionPane.showMessageDialog(this, "Please upload a test file.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!isTestFileValid) {
            JOptionPane.showMessageDialog(this, "The file you have entered is not valid. Please upload again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(!domainName.startsWith("http://"))
            domainName = "http://" + domainName;
        
        DbHandler dbHandler = new DbHandler();
        String[] fileContents = testFileContents.split(";");
        String searchTerm = fileContents[0];
        String skuList = fileContents[1];
        boolean entryResults = dbHandler.createEntry(domainName, searchTerm, skuList);
        if(!entryResults) {
            JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    
    private void uploadTestFileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        File testFile = fileChooser.getSelectedFile(); 
        
        try{
            String fileData;
        
            StringBuilder fileDataBuilder = new StringBuilder(1000);
            BufferedReader reader = new BufferedReader(new FileReader(testFile));
 
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileDataBuilder.append(readData);
                buf = new char[1024];
            }                
            reader.close();
            fileData = fileDataBuilder.toString();
            System.out.println(fileData);
            String regex="[a-zA-Z0-9 ]*;[a-zA-Z0-9]*[,[a-zA-Z0-9]+]*";
            if(!fileData.matches(regex))
                isTestFileValid = false;
            else
                isTestFileValid = true;
            
            isTestFileUploaded = true;
            testFileContents = fileData;
    
       }
       catch(Exception e)
       {
            
       }
    }
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        CardLayout cards = (CardLayout)mainPanel.getLayout();
        cards.show(mainPanel, "card2");
    }
}

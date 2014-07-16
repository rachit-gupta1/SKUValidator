/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skuvalidator;

import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author rachit
 */
public class ModifyProfileView extends JPanel {

    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField domainTextField;
    private javax.swing.JLabel userEmailLabel;
    private javax.swing.JTextField userEmailField;
    private javax.swing.JButton submitButton;
    private javax.swing.JButton uploadTestFileButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton cancelButton;
    
    private javax.swing.JPanel mainPanel;
    private ProfileDetails selectedProfile;
    private boolean isTestFileValid;
    private boolean isTestFileChanged;
    private String testFileContents;

    public ModifyProfileView(javax.swing.JPanel parent, ProfileDetails details) {

        mainPanel = parent;
        selectedProfile = details;
        isTestFileValid = false;
        isTestFileChanged = false;

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(skuvalidator.SKUValidatorApp.class).getContext().getResourceMap(SKUValidatorView.class);

        jLabel1 = new javax.swing.JLabel();
        jLabel1.setText(resourceMap.getString("jLabel1.text"));
        jLabel1.setName("jLabel1");

        domainTextField = new javax.swing.JTextField();
        domainTextField.setText(resourceMap.getString("domainTextField.text"));
        domainTextField.setName("domainTextField");
        domainTextField.setText(selectedProfile.getDomainName());

        userEmailLabel = new javax.swing.JLabel();
        userEmailLabel.setText(resourceMap.getString("userEmailLabel.text"));
        userEmailLabel.setName("userEmailLabel");

        userEmailField = new javax.swing.JTextField();
        userEmailField.setName("userEmailField");
        userEmailField.setText(selectedProfile.getUserEmail());

        submitButton = new javax.swing.JButton();
        submitButton.setText(resourceMap.getString("submitButton.text"));
        submitButton.setName("submitButton");
        submitButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        uploadTestFileButton = new javax.swing.JButton();
        uploadTestFileButton.setText(resourceMap.getString("uploadTestFileButton.text"));
        uploadTestFileButton.setName("uploadTestFileButton");
        uploadTestFileButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uploadTestFileButtonActionPerformed(evt);
            }
        });

        filler1 = new javax.swing.Box.Filler(null, null, null);
        filler1.setName("filler1");

        cancelButton = new javax.swing.JButton();
        cancelButton.setText(resourceMap.getString("cancelButton.text"));
        cancelButton.setName("cancelButton");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {

            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Panel2Layout = new javax.swing.GroupLayout(this);
        setLayout(Panel2Layout);
        Panel2Layout.setHorizontalGroup(
                Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(Panel2Layout.createSequentialGroup().addContainerGap().addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(Panel2Layout.createSequentialGroup().addComponent(jLabel1).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE).addComponent(domainTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(Panel2Layout.createSequentialGroup().addComponent(userEmailLabel).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE).addComponent(userEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(Panel2Layout.createSequentialGroup().addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(submitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE).addComponent(uploadTestFileButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(111, 111, 111).addComponent(cancelButton, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))).addContainerGap()));
        Panel2Layout.setVerticalGroup(
                Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(Panel2Layout.createSequentialGroup().addContainerGap().addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1).addComponent(domainTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(userEmailLabel).addComponent(userEmailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGap(18, 18, 18).addComponent(uploadTestFileButton, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE).addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(Panel2Layout.createSequentialGroup().addGap(33, 33, 33).addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)).addGroup(Panel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(submitButton).addComponent(cancelButton))).addContainerGap(45, Short.MAX_VALUE)));

    }

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:

        String domainName = domainTextField.getText();
        String userEmail = userEmailField.getText();
        String timeStamp = new SimpleDateFormat("dd-mm-yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
        if(!isTestFileChanged)
            isTestFileValid = true;

        if ("".equals(domainName)) {
            JOptionPane.showMessageDialog(this, "The domain name cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if ("".equals(userEmail)) {
            JOptionPane.showMessageDialog(this, "The Email ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!isTestFileValid) {
            JOptionPane.showMessageDialog(this, "The file you have entered is not valid. Please upload again.", "Error", JOptionPane.ERROR_MESSAGE);
            testFileContents = "";
            return;
        }

        String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (!userEmail.matches(EMAIL_PATTERN)) {
            JOptionPane.showMessageDialog(this, "Please enter a valid Email ID.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!domainName.startsWith("http://")) {
            domainName = "http://" + domainName;
        }

        DbHandler dbHandler = new DbHandler();
        String searchTerm, skuList;
        if(isTestFileChanged) {
        String[] fileContents = testFileContents.split(";");
        searchTerm = fileContents[0];
        skuList = fileContents[1];
        }
        else {
            searchTerm = selectedProfile.getSearchTerm();
            skuList = selectedProfile.getSKUList();
        }
        String id = selectedProfile.getProfileID();
        boolean entryResults = dbHandler.modifyEntry(id, userEmail, domainName, searchTerm, skuList, timeStamp);
        if (!entryResults) {
            JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Success.", "Message", JOptionPane.INFORMATION_MESSAGE);
            CardLayout cards = (CardLayout) mainPanel.getLayout();
            JPanel listProfile = new ListProfilesView(mainPanel);
            mainPanel.add(listProfile, "card4");
            cards.show(mainPanel, "card4");
        }
    }

    private void uploadTestFileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(this);
        File testFile = fileChooser.getSelectedFile();

        if (testFile != null) {
            try {
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
                String regex = "[a-zA-Z0-9 ]*;[ a-zA-Z0-9\r\n]*[,[ a-zA-Z0-9\r\n]+]*";
                if (!fileData.matches(regex)) {
                    isTestFileValid = false;
                } else {
                    isTestFileValid = true;
                }

                testFileContents = fileData;
                isTestFileChanged = true;

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        CardLayout cards = (CardLayout) mainPanel.getLayout();
        cards.show(mainPanel, "card2");
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skuvalidator;

import java.awt.CardLayout;
import javax.swing.JPanel;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author rachit
 */
public class ListProfilesView extends JPanel {
    
    JPanel mainPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList profileList;
    private javax.swing.JButton deleteProfileButton;
    private javax.swing.JButton modifyProfileButton;
    private javax.swing.JButton runProfileButton;
    private javax.swing.JButton okButton;
    
    List<ProfileDetails> resultList;
    ProfileDetails selectedProfile;

    public ListProfilesView(JPanel parent) {
        
        mainPanel = parent;
        selectedProfile = new ProfileDetails();
        
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(skuvalidator.SKUValidatorApp.class).getContext().getResourceMap(SKUValidatorView.class);
        
        profileList = new javax.swing.JList();
        profileList.setName("profileList");
        profileList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {

            public void valueChanged(ListSelectionEvent arg0) {
                selectedProfile = resultList.get(profileList.getSelectedIndex());
            }
        });
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setName("jScrollPane1"); 
        jScrollPane1.setViewportView(profileList);
        
        deleteProfileButton = new javax.swing.JButton();
        deleteProfileButton.setText(resourceMap.getString("deleteProfileButton.text")); 
        deleteProfileButton.setName("deleteProfileButton"); 
        deleteProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProfileButtonActionPerformed(evt);
            }
        });
        
        modifyProfileButton = new javax.swing.JButton();
        modifyProfileButton.setText(resourceMap.getString("modifyProfileButton.text")); 
        modifyProfileButton.setName("modifyProfileButton"); 
        modifyProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyProfileButtonActionPerformed(evt);
            }
        });
        
        runProfileButton = new javax.swing.JButton();
        runProfileButton.setText(resourceMap.getString("runProfileButton.text")); 
        runProfileButton.setName("runProfileButton"); 
        runProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runProfileButtonActionPerformed(evt);
            }
        });        
        
        okButton = new javax.swing.JButton();
        okButton.setText(resourceMap.getString("okButton.text")); 
        okButton.setName("okButton"); 
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });


        
        javax.swing.GroupLayout Panel3Layout = new javax.swing.GroupLayout(this);
        setLayout(Panel3Layout);
        Panel3Layout.setHorizontalGroup(
            Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(modifyProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                            .addComponent(runProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, Panel3Layout.createSequentialGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))))
        );
        Panel3Layout.setVerticalGroup(
            Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel3Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(runProfileButton)
                        .addGap(45, 45, 45)
                        .addComponent(modifyProfileButton)
                        .addGap(46, 46, 46)
                        .addComponent(deleteProfileButton))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(okButton)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        
        populateProfileList();
    }
    
    private void deleteProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
     private void modifyProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
     
    private void runProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
    
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        CardLayout cards = (CardLayout)mainPanel.getLayout();
        cards.show(mainPanel, "card2");
    }
    
    private void populateProfileList() {
        DbHandler dbHandler = new DbHandler();
        resultList = dbHandler.getAllEntries();
        DefaultListModel listModel = new DefaultListModel();
        for (ProfileDetails profileDetails : resultList) {
            listModel.addElement(profileDetails.getDomainName() + "; " + profileDetails.getTimeStamp());
        }
        profileList.setModel(listModel);
        profileList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        profileList.setLayoutOrientation(JList.VERTICAL);
        profileList.setVisibleRowCount(-1);
    }
    
    
}

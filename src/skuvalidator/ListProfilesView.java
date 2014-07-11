/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skuvalidator;

import java.awt.CardLayout;
import javax.swing.JPanel;

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

    public ListProfilesView(JPanel parent) {
        
        mainPanel = parent;
        
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(skuvalidator.SKUValidatorApp.class).getContext().getResourceMap(SKUValidatorView.class);
        
        profileList = new javax.swing.JList();
        profileList.setName("profileList"); // NOI18N
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane1.setName("jScrollPane1"); // NOI18N
        jScrollPane1.setViewportView(profileList);
        
        deleteProfileButton = new javax.swing.JButton();
        deleteProfileButton.setText(resourceMap.getString("deleteProfileButton.text")); // NOI18N
        deleteProfileButton.setName("deleteProfileButton"); // NOI18N
        deleteProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteProfileButtonActionPerformed(evt);
            }
        });
        
        modifyProfileButton = new javax.swing.JButton();
        modifyProfileButton.setText(resourceMap.getString("modifyProfileButton.text")); // NOI18N
        modifyProfileButton.setName("modifyProfileButton"); // NOI18N
        modifyProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyProfileButtonActionPerformed(evt);
            }
        });
        
        runProfileButton = new javax.swing.JButton();
        runProfileButton.setText(resourceMap.getString("runProfileButton.text")); // NOI18N
        runProfileButton.setName("runProfileButton"); // NOI18N
        runProfileButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runProfileButtonActionPerformed(evt);
            }
        });        
        
        okButton = new javax.swing.JButton();
        okButton.setText(resourceMap.getString("okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N
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
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
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
    
    
}

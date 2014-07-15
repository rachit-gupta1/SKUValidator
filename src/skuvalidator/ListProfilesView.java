/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skuvalidator;

import java.awt.CardLayout;
import java.util.Arrays;
import javax.swing.JPanel;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

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
    private javax.swing.JLabel statusLabel;
    
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
                int selectedIndex = profileList.getSelectedIndex();
                if(selectedIndex != -1)
                    selectedProfile = resultList.get(selectedIndex);
                else
                    selectedProfile = new ProfileDetails();
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

        statusLabel = new javax.swing.JLabel();
        
        javax.swing.GroupLayout Panel3Layout = new javax.swing.GroupLayout(this);
        setLayout(Panel3Layout);
        Panel3Layout.setHorizontalGroup(
            Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Panel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(Panel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(Panel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(deleteProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(modifyProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(runProfileButton, javax.swing.GroupLayout.DEFAULT_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, Panel3Layout.createParallelGroup()
                        .addComponent(okButton, javax.swing.GroupLayout.DEFAULT_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                         .addComponent(statusLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114)))));
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
                .addGap(18, 18, 18)
                .addComponent(statusLabel)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        
        populateProfileList();
    }
    
    private void deleteProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        String id = selectedProfile.getProfileID();
        if(id.equals(""))
            return;
        
        int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this profile?");
        if(response == JOptionPane.YES_OPTION) {
            DbHandler dbHandler = new DbHandler();
            boolean deleteRes = dbHandler.deleteEntry(id);
            if(!deleteRes) {
                JOptionPane.showMessageDialog(this, "Something went wrong. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            else {
                populateProfileList();
            }
        }
    }
    
     private void modifyProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
    }
     
    private void runProfileButtonActionPerformed(java.awt.event.ActionEvent evt) {
        boolean result = true;
        String domainName = selectedProfile.getDomainName();
        String searchTerm = selectedProfile.getSearchTerm();
        String[] skuString = selectedProfile.getSKUList().split(",");
        List<String> skuList = Arrays.asList(skuString);
        domainName += "/find/" + searchTerm.replaceAll(" ", "-");
        HttpRequestHandler httpHandler = new HttpRequestHandler(domainName);
        String httpResult = httpHandler.ExecuteRequest();
        Document doc = Jsoup.parse(httpResult);
        Elements skuElements = doc.getElementsByAttribute("data-sku");
        for (Element sku : skuElements) {
            String skuValue = sku.attr("data-sku");
            if(!skuList.contains(skuValue)) {
                result = false;
                statusLabel.setText("SKU Not Found: " + skuValue);
                break;
            }
        }
        
        if(result && !httpResult.equals("")) {
            statusLabel.setText("All SKUs were found.");
        }
    }
    
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        CardLayout cards = (CardLayout)mainPanel.getLayout();
        cards.show(mainPanel, "card2");
    }
    
    private void populateProfileList() {
        DbHandler dbHandler = new DbHandler();
        resultList = null;
        resultList = dbHandler.getAllEntries();
        DefaultListModel listModel = new DefaultListModel();
        for (ProfileDetails profileDetails : resultList) {
            listModel.addElement(profileDetails.getDomainName() + ": " + profileDetails.getSearchTerm());
        }
        profileList.setModel(listModel);
        profileList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        profileList.setLayoutOrientation(JList.VERTICAL);
    }
    
    
}

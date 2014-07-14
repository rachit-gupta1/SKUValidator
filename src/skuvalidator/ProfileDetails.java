/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package skuvalidator;

/**
 *
 * @author rachit
 */
public class ProfileDetails {
    
    private String profileId;
    private String userEmail;
    private String domainName;
    private String searchTerm;
    private String timeStamp;
    private String skuList;
    
    public ProfileDetails() {
        profileId = "";
        userEmail = "";
        domainName = "";
        searchTerm = "";
        timeStamp = "";
        skuList = "";
    }
    
    public ProfileDetails(String id, String email, String domain, String search, String time, String list) {
        profileId = id;
        userEmail = email;
        domainName = domain;
        searchTerm = search;
        timeStamp = time;
        skuList = list;
    }
    
    String getProfileID() { return profileId; }
    String getUserEmail() { return userEmail; }
    String getDomainName() { return domainName; }
    String getSearchTerm() { return searchTerm; }
    String getTimeStamp() { return timeStamp; }
    String getSKUList() { return skuList; }
    
}

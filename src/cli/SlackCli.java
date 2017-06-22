/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.prefs.Preferences;
import slackstatus.SlackStatus;
import slackstatus.SlackUtils;

/**
 *
 * @author joshuagrierson
 */
public class SlackCli {
    
    private final String[] args;
    
    private SlackCliRequest mSlackCliRequest;
    
    private Preferences mSlackPrefs;
    
    public SlackCli (String[] args) {
        this.args = args;
    }
    
    public void sendRequest () {
        
        final String token = this.getSlackToken();
        
        if(token != null) {
            
            final String[] days = this.getSlackDays();
            final String currentDay = LocalDate.now().getDayOfWeek().name();
            
            this.mSlackCliRequest = new SlackCliRequest(this.getSlackToken());
            
            if(Arrays.asList(days).indexOf(currentDay) > -1) {
                
                this.mSlackCliRequest.setPresence(true);
                
            } else {
                
                this.mSlackCliRequest.setPresence(false);
                
            }
            
        } else {
            
            System.err.println("User not logged in Slack...please login");
            
        }
        
    }
    
    private String getSlackToken () {
        
        try {
            
            this.mSlackPrefs = Preferences.userRoot().node(SlackStatus.APP_PREFS_NAME);
            if(this.mSlackPrefs.get(SlackStatus.APP_PREFS_TOKEN_KEY, null) != null) {
                
                return this.mSlackPrefs.get(SlackStatus.APP_PREFS_TOKEN_KEY, null);
                
            }
            
        } catch (Exception ex) {
            
            System.err.println("Security Error: " + ex.getMessage());
            ex.printStackTrace();
            
        }
        
        return null;
        
    }
    
    private String[] getSlackDays () {
        
        String foundDays = "";
        
        try {
            
            this.mSlackPrefs = Preferences.userRoot().node(SlackStatus.APP_PREFS_NAME);
            
            if(this.mSlackPrefs.get(SlackStatus.APP_PREFS_DAYS_KEY, null) != null) {
                
                final String[] days = SlackUtils.getArray(this.mSlackPrefs.get(SlackStatus.APP_PREFS_DAYS_KEY, null));
                for(int i = 0; i < days.length; i++) {
                    
                    final String day = days[i].split(":")[0].toUpperCase();
                    foundDays += (day + ",");
                    
                }
                
            }
            
        } catch (Exception ex) {
            
            System.err.println("Security Error: " + ex.getMessage());
            ex.printStackTrace();
            
        }
        
        return (foundDays != "" ? SlackUtils.getArray(foundDays) : new String[]{});
        
    }
    
    public static SlackCli newInstance (String[] args) {
        return new SlackCli(args);
    }
    
}

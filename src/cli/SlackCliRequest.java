/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cli;

import slackstatus.SlackHttp;
import slackstatus.SlackJSONParser;
import slackstatus.SlackQueryBuilder;
import slackstatus.SlackStatus;

/**
 *
 * @author joshuagrierson
 */
public class SlackCliRequest {
    
    private final SlackQueryBuilder mSlackQuery;
    
    public SlackCliRequest (String token) {
        
        this.mSlackQuery = SlackQueryBuilder.newInstance(SlackStatus.SLACK_USERS_PRESENCE)
                .addParam("token", token);
        
    }
    
    public void setPresence (boolean away) {
        
        if(away) {
            this.mSlackQuery.addParam("presence", "away");
        } else {
            this.mSlackQuery.addParam("presence", "auto");
        }
        
        final String response = SlackHttp.newInstance(this.mSlackQuery)
                .post().open().getResponse();
        
        if(response != null) {
            
            final boolean set = (Boolean) SlackJSONParser.newInstance().getHashMap(response).get("ok");
            
            if(set) {
                System.out.println("Updated presence successfully");
            } else {
                System.out.println("Unable to set presence");
            }
            
        }
        
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.events;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javafx.event.Event;
import javafx.event.EventHandler;
import slackstatus.SlackQueryBuilder;
import slackstatus.SlackStatus;

/**
 *
 * @author Joshua
 */
public class CollectAuthCode implements EventHandler {
    
    private final String mSlackAuthURL;
    
    public CollectAuthCode () {
        
        this.mSlackAuthURL = new String(
                SlackQueryBuilder.newInstance(SlackStatus.SLACK_AUTH_URL)
                .addParam("client_id", SlackStatus.SLACK_CLIENT_ID)
                .addParam("scope", SlackStatus.SLACK_APP_SCOPE).getUrl()
        );
        
    }

    @Override
    public void handle(Event event) {
        
        try {
            
            Desktop.getDesktop().browse(new URI(this.mSlackAuthURL));
            
        } catch (IOException | URISyntaxException ex) {
            
            System.err.println(ex.getMessage());
            System.exit(0);
            
        }
        
    }
    
}

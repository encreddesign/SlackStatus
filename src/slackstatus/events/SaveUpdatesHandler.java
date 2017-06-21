/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import slackstatus.views.SlackSignedInView;

/**
 *
 * @author joshuagrierson
 */
public class SaveUpdatesHandler implements EventHandler {
    
    private final SlackSignedInView mSlackSignedInView;
    
    public SaveUpdatesHandler (SlackSignedInView view) {
        this.mSlackSignedInView = view;
    }

    @Override
    public void handle(Event event) {
        
    }
    
}

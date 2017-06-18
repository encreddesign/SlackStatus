/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import slackstatus.SlackHttpThread;
import slackstatus.SlackStatus;
import slackstatus.views.SlackView;

/**
 *
 * @author Joshua
 */
public class AuthenticateUser implements EventHandler {
    
    private final Label mSlackErrorLabel;
    private final TextField mSlackAuthCodeField;
    
    private final SlackView mSlackView;
    
    public AuthenticateUser (SlackView view) {
        
        this.mSlackView = view;
        
        this.mSlackErrorLabel = view.mSlackErrorLabel;
        this.mSlackAuthCodeField = view.mSlackCodeField;
        
    }

    @Override
    public void handle(Event event) {
        
        try {
            
            if(this.mSlackAuthCodeField.getText().isEmpty()) {
                throw new Exception("Authentication code is needed");
            }
            
            this.updateElementState(event, true, "Signing In...");
            this.handleDaemonThread();
            
        } catch (Exception ex) {
            
            this.errorUi(ex.getMessage());
            this.updateElementState(event, false, "Add User");
            
            System.err.println("Error: " + ex.getMessage());
            
        }
        
    }
    
    private void updateElementState (Event event, boolean disabled, String message) {
        
        final Button button = ((Button) event.getSource());
        
        button.setDisable(disabled);
        button.setText(message);
        
    }
    
    private void handleDaemonThread () {
        
        final SlackHttpThread httpThread = new SlackHttpThread(this.mSlackView);
        
        httpThread.setDaemon(true);
        
        httpThread.setUrl(SlackStatus.SLACK_TOKEN_URL);
        httpThread.addParam("client_id", SlackStatus.SLACK_CLIENT_ID);
        httpThread.addParam("client_secret", SlackStatus.SLACK_CLIENT_SECRET);
        httpThread.addParam("code", this.mSlackAuthCodeField.getText().trim());
        
        httpThread.setCallback(new ServerAPIResponse());
        
        httpThread.start();
        
    }
    
    private void errorUi (String message) {
        
        this.mSlackErrorLabel.setText(message);
        this.mSlackErrorLabel.setVisible(true);
        
    }
    
}

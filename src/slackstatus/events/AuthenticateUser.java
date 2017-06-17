/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import slackstatus.SlackHttpThread;

/**
 *
 * @author Joshua
 */
public class AuthenticateUser implements EventHandler {
    
    protected volatile Thread mDaemonThread;
    
    private final Label mSlackErrorLabel;
    private final TextField mSlackAuthCodeField;
    
    public AuthenticateUser (TextField codeField, Label errorLabel) {
        
        this.mDaemonThread = new SlackHttpThread();
        this.mDaemonThread.setDaemon(true);
        
        this.mSlackErrorLabel = errorLabel;
        this.mSlackAuthCodeField = codeField;
        
    }

    @Override
    public void handle(Event event) {
        
        try {
            
            if(this.mSlackAuthCodeField.getText().isEmpty()) {
                throw new Exception("Authentication code is needed");
            }
            
            this.mDaemonThread.start();
            
        } catch (Exception ex) {
            
            this.errorUi(ex.getMessage());
            System.err.println("Error: " + ex.getMessage());
            
        }
        
    }
    
    private void errorUi (String message) {
        
        this.mSlackErrorLabel.setText(message);
        this.mSlackErrorLabel.setVisible(true);
        
    }
    
}

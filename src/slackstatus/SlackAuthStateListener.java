/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.scene.web.WebEngine;

/**
 *
 * @author joshuagrierson
 */
public class SlackAuthStateListener implements ChangeListener<State> {
    
    private final WebEngine mWebEngine;
    
    public SlackAuthStateListener (WebEngine engine) {
        this.mWebEngine = engine;
    }

    @Override
    public void changed(ObservableValue<? extends State> observable, State oldState, State newState) {
        
        if(newState == State.SUCCEEDED) {
            
            System.out.println("Page Loaded");
            
        } else if(newState == State.FAILED) {
            
            System.err.println("Page Failed");
            
        }
        
    }
    
}

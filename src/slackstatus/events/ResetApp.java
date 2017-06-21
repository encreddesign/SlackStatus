/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.events;

import java.util.prefs.Preferences;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slackstatus.SlackStatus;
import slackstatus.views.SlackSignedInView;
import slackstatus.views.SlackView;

/**
 *
 * @author Joshua
 */
public class ResetApp implements EventHandler {
    
    private final SlackView mSlackView;
    private final SlackSignedInView mSlackSignedInView;
    
    private Preferences mSlackPrefs;
    
    public ResetApp (SlackSignedInView view) {
        
        this.mSlackView = new SlackView();
        this.mSlackView.setAlignment(Pos.CENTER);
        this.mSlackView.setVgap(10);
        this.mSlackView.setHgap(10);
        this.mSlackView.setPadding(new Insets(25, 25, 25, 25));
        
        this.mSlackSignedInView = view;
        
    }

    @Override
    public void handle(Event event) {
        
        try {
            
            this.mSlackPrefs = Preferences.userRoot().node(SlackStatus.APP_PREFS_NAME);
            
            this.mSlackPrefs.remove(SlackStatus.APP_PREFS_TOKEN_KEY);
            this.mSlackPrefs.remove(SlackStatus.APP_PREFS_DAYS_KEY);
            
            ((Stage) mSlackSignedInView.getScene().getWindow()).setScene(
                new Scene(this.mSlackView, SlackStatus.APP_WIDTH, SlackStatus.APP_HEIGHT));
            
        } catch (Exception ex) {
            
            this.mSlackSignedInView.mSlackErrorLabel.setText(ex.getMessage());
            System.err.println("Security Error: " + ex.getMessage());
            ex.printStackTrace();
            
        }
        
    }
    
}

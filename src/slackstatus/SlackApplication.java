/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import java.util.prefs.Preferences;
import slackstatus.views.SlackView;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static slackstatus.SlackStatus.APP_HEIGHT;
import static slackstatus.SlackStatus.APP_WIDTH;
import slackstatus.events.SignedInCallback;
import slackstatus.views.SlackAbstractView;
import slackstatus.views.SlackSignedInView;

/**
 *
 * @author Joshua
 */
public class SlackApplication extends Application {
    
    private Scene mScene;
    
    private SlackView mSlackView;
    private SlackSignedInView mSlackSignedInView;
    
    private Preferences mSlackPrefs;

    @Override
    public void start (final Stage stage) {
        
        stage.setTitle(SlackStatus.APP_TITLE);
        
        this.mSlackView = new SlackView();
        this.mSlackView.setAlignment(Pos.CENTER);
        this.mSlackView.setVgap(10);
        this.mSlackView.setHgap(10);
        this.mSlackView.setPadding(new Insets(25, 25, 25, 25));
        
        this.mScene = new Scene(this.mSlackView, APP_WIDTH, APP_HEIGHT);
        stage.setScene(this.mScene);
        stage.show();
        
        this.checkSlackToken(stage);
        
    }
    
    void checkSlackToken (Stage stage) {
        
        try {
            
            this.mSlackPrefs = Preferences.userRoot().node(SlackStatus.APP_PREFS_NAME);
            if(this.mSlackPrefs.get(SlackStatus.APP_PREFS_TOKEN_KEY, null) != null) {
                
                this.handleDaemonThread(this.mSlackPrefs.get(SlackStatus.APP_PREFS_TOKEN_KEY, null));
                
            }
            
        } catch (SecurityException ex) {
            
            System.err.println("Security Error: " + ex.getMessage());
            
        }
        
    }
    
    private void handleDaemonThread (String token) {
        
        final SlackHttpThread httpThread = new SlackHttpThread(this.mSlackView);
        
        httpThread.setDaemon(true);
        
        httpThread.setUrl(SlackStatus.SLACK_AUTH_TEST);
        httpThread.addParam("token", token);
        
        httpThread.setCallback(new SignedInCallback());
        
        httpThread.start();
        
    }
    
    public void launchApp (String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static slackstatus.SlackStatus.APP_HEIGHT;
import static slackstatus.SlackStatus.APP_WIDTH;
import slackstatus.events.SignedInCallback;
import slackstatus.views.SlackAbstractView;

/**
 *
 * @author Joshua
 */
public class SlackApplication extends Application {
    
    private Scene mScene;
    
    private PlaceholderView mPlaceholderView;
    
    private Preferences mSlackPrefs;

    @Override
    public void start (final Stage stage) {
        
        stage.setTitle(SlackStatus.APP_TITLE);
        
        this.mPlaceholderView = new PlaceholderView();
        
        this.mScene = new Scene(this.mPlaceholderView, APP_WIDTH, APP_HEIGHT);
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
            
        } catch (Exception ex) {
            
            System.err.println("Security Error: " + ex.getMessage());
            ex.printStackTrace();
            
        }
        
    }
    
    private void handleDaemonThread (String token) {
        
        final SlackHttpThread httpThread = new SlackHttpThread(this.mPlaceholderView);
        
        httpThread.setDaemon(true);
        
        httpThread.setUrl(SlackStatus.SLACK_AUTH_TEST);
        httpThread.addParam("token", token);
        
        httpThread.setCallback(new SignedInCallback());
        
        httpThread.start();
        
    }
    
    private final class PlaceholderView extends SlackAbstractView {
        
        public PlaceholderView () {}
        
    }
    
    public void launchApp (String[] args) {
        launch(args);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import javafx.application.Application;
import static javafx.application.Application.STYLESHEET_MODENA;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author joshuagrierson
 */
public class SlackStatus extends Application {
    
    private Scene mScene;
    
    private final static int APP_WIDTH = 500;
    private final static int APP_HEIGHT = 500;
    private final static String APP_TITLE = "Slack Status v1.0";
    
    public final static String SLACK_AUTH_URL = "https://slack.com/oauth/authorize";
    public final static String SLACK_CLIENT_ID = "";
    public final static String SLACK_APP_SCOPE = "";
    
    @Override
    public void start (final Stage stage) {
        
        stage.setTitle(STYLESHEET_MODENA);
        
        this.mScene = new Scene(new SlackBrowserView(), APP_WIDTH, APP_HEIGHT);
        
        stage.setScene(this.mScene);
        stage.show();
        
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}

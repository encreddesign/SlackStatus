/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static slackstatus.SlackStatus.APP_HEIGHT;
import static slackstatus.SlackStatus.APP_WIDTH;

/**
 *
 * @author Joshua
 */
public class SlackApplication extends Application {
    
    private Scene mScene;
    private SlackView mSlackView;

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
        
    }
    
    public void launchApp (String[] args) {
        launch(args);
    }
    
}

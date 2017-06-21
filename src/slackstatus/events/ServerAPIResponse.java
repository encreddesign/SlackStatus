/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slackstatus.events;

import java.util.HashMap;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import slackstatus.SlackHttpInterface;
import slackstatus.SlackStatus;
import slackstatus.views.SlackAbstractView;
import slackstatus.views.SlackSignedInView;
import slackstatus.views.SlackView;

/**
 *
 * @author Joshua
 */
public class ServerAPIResponse implements SlackHttpInterface {
    
    private SlackView mSlackView;
    private SlackSignedInView mSlackSignedInView;
    
    private HashMap<String, String> mServerResponse;
    
    public ServerAPIResponse () {}
    
    @Override
    public void PreResponse (SlackAbstractView view) {}

    @Override
    public void OnResponse (SlackAbstractView view, HashMap<String, String> response) {
        
        this.mSlackView = (SlackView)view;
        this.mServerResponse = response;
        
        this.mSlackView.mSlackCommitButton.setDisable(false);
        this.mSlackView.mSlackCommitButton.setText("Add User");
        
        if(this.mServerResponse.get("error") != null) {
            this.mSlackView.mSlackErrorLabel.setText("API: " + this.mServerResponse.get("error"));
        } else if(this.mServerResponse.get("access_token") != null) {
            
            this.loadSignedInView();
            
        }
        
    }
    
    void loadSignedInView () {
        
        this.mSlackSignedInView = new SlackSignedInView(this.mServerResponse);
        this.mSlackSignedInView.setAlignment(Pos.CENTER);
        this.mSlackSignedInView.setVgap(10);
        this.mSlackSignedInView.setHgap(10);
        this.mSlackSignedInView.setPadding(new Insets(25, 25, 25, 25));
        
        ((Stage) this.mSlackView.getScene().getWindow()).setScene(
                new Scene(this.mSlackSignedInView, SlackStatus.APP_WIDTH, SlackStatus.APP_HEIGHT));
        
    }
    
}
